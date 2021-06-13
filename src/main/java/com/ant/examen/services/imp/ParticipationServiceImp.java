package com.ant.examen.services.imp;

import com.ant.examen.responses.MessageResponse;
import com.ant.examen.responses.ParticipationResponse;
import com.ant.examen.responses.QuestionResponse;
import com.ant.examen.responses.ReponseCandidatResponse;
import com.ant.examen.entities.*;
import com.ant.examen.repository.*;
import com.ant.examen.services.ParticipationService;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class ParticipationServiceImp implements ParticipationService {
    @Autowired
    private ParticipationRepository participationRepository;
    @Autowired
    private QuestionRepository questionRepository;
    @Autowired
    private ReponseCandidatRepository reponseCandidatRepository;
    @Autowired
    private ExamenRepository examenRepository;
    @Autowired
    private ReponseRepository reponseRepository;

    @Transactional
    @Override
    public ParticipationResponse save(Integer idCandidat, Integer idExamen) {
        ModelMapper modelMapper = new ModelMapper();
        Participation participation = new Participation();
        ParticipationId id = new ParticipationId();
        id.setExamenId(idExamen);
        id.setCandidatId(idCandidat);
        participation.setId(id);

        participation.setDateParticipation(new Date());


        Participation parti = participationRepository.findById(participation.getId()).orElse(null);
        ParticipationResponse participationResponse = new ParticipationResponse();
        if (parti == null) {
            participationRepository.save(participation);
            Examen examen = examenRepository.getById(participation.getId().getExamenId());
            participation.setExamen(examen);
            List<Question> questions = questionRepository.findRandomQuestion(examen.getTheme(),
                    PageRequest.of(0, examen.getNbrQuestion()));
            List<QuestionResponse> questionResponses = new ArrayList<>();
            for (Question ques : questions) {

                List<Reponse> responses = reponseRepository.findByQuestion(ques);

                List<ReponseCandidat> reponseCandidats = new ArrayList<>();
                for (Reponse rep : responses) {
                    ReponseCandidat reponseCandidat = new ReponseCandidat();
                    reponseCandidat.setReponse(rep);
                    reponseCandidat.setParticipation(participation);
                    reponseCandidatRepository.save(reponseCandidat);
                    reponseCandidats.add(reponseCandidat);

                }

                QuestionResponse questionResponse = new QuestionResponse();
                questionResponse.setQuestion(ques);
                List<ReponseCandidatResponse> reponseCandidatResponses = modelMapper.map(reponseCandidats,
                        new TypeToken<List<ReponseCandidatResponse>>() {
                        }.getType());
                questionResponse.setReponses(reponseCandidatResponses);
                questionResponses.add(questionResponse);
            }
            participationResponse.setParticipation(participation);
            participationResponse.setQuestions(questionResponses);

        } else {
            participationResponse.setParticipation(parti);
            List<Question> questions = questionRepository.findQuestionByParticipation(participation);
            List<QuestionResponse> questionResponses = new ArrayList<>();
            for (Question question : questions) {
                QuestionResponse questionResponse = new QuestionResponse();
                questionResponse.setQuestion(question);
                List<ReponseCandidat> reponseCandidats = reponseCandidatRepository.findByQuestion(question, parti);
                List<ReponseCandidatResponse> reponseCandidatResponses = modelMapper.map(reponseCandidats,
                        new TypeToken<List<ReponseCandidatResponse>>() {
                        }.getType());
                questionResponse.setReponses(reponseCandidatResponses);
                questionResponses.add(questionResponse);
            }
            participationResponse.setQuestions(questionResponses);
        }

        return participationResponse;
    }

    @Override
    public MessageResponse finishParticipation(Participation participation) {
        participation.setFini(true);
        double score = 0;

        List<Question> questions = questionRepository.findQuestionByParticipation(participation);


        for (Question question : questions) {
           System.out.println(question.getLibelle());
            boolean isCorrect = true;
            List<ReponseCandidat> reponseCandidats = reponseCandidatRepository.findByQuestion(question, participation);
            System.out.println(reponseCandidats.size() + "**************************");
            for (ReponseCandidat rep : reponseCandidats) {
                System.out.println(rep.getReponse().getLibelle());
                System.out.println(rep.isStatut() + "******" + rep.getReponse().isCorrect());
                if (rep.isStatut() != rep.getReponse().isCorrect()) {
                    isCorrect = false;
                }
            }
            System.out.println("***********************************************************");
            if (isCorrect) {
                score++;
            }

        }
        System.out.println("score =======" + score);
        participation.setScore(score);
        participationRepository.save(participation);
        return new MessageResponse(true, "Succès", "Examen Terminée");
    }

    @Override
    public List<Participation> findByCandidat(Integer id) {
        Candidat candidat = new Candidat();
        candidat.setId(id);
        return participationRepository.findByCandidat(candidat);
    }

    @Override
    public List<Participation> findAll() {
        return participationRepository.findAll(Sort.by("score").descending().and(Sort.by("examen")));
    }
}
