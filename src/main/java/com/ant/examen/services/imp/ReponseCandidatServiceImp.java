package com.ant.examen.services.imp;

import com.ant.examen.responses.MessageResponse;
import com.ant.examen.responses.ParticipationResultResponse;
import com.ant.examen.responses.QuestionResultResponse;
import com.ant.examen.entities.Participation;
import com.ant.examen.entities.ParticipationId;
import com.ant.examen.entities.Question;
import com.ant.examen.entities.ReponseCandidat;
import com.ant.examen.repository.ParticipationRepository;
import com.ant.examen.repository.QuestionRepository;
import com.ant.examen.repository.ReponseCandidatRepository;
import com.ant.examen.services.ReponseCandidatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class ReponseCandidatServiceImp implements ReponseCandidatService {
    @Autowired
    private ReponseCandidatRepository reponseCandidatRepository;
    @Autowired
    private QuestionRepository questionRepository;
    @Autowired
    private ParticipationRepository participationRepository;

    @Transactional
    @Override
    public MessageResponse save(List<ReponseCandidat> reponseCandidats) {
        reponseCandidatRepository.saveAll(reponseCandidats);
        return new MessageResponse(true, "Succès", "Opération effectuée");
    }

    @Override
    public ParticipationResultResponse findByParticipation(Integer idExamen, Integer idCandidat) {
        ParticipationResultResponse participationResultResponse = new ParticipationResultResponse();

        Participation participation = new Participation();
        ParticipationId id = new ParticipationId();
        id.setCandidatId(idCandidat);
        id.setExamenId(idExamen);
        participation.setId(id);
        participation = participationRepository.findById(id).orElse(null);
        participationResultResponse.setParticipation(participation);
        List<QuestionResultResponse> questionResultResponses = new ArrayList<>();
        List<Question> questions = questionRepository.findQuestionByParticipation(participation);
        for (Question question : questions) {

            List<ReponseCandidat> reponseCandidats = reponseCandidatRepository.findByQuestion(question, participation);
            QuestionResultResponse questionResultResponse = new QuestionResultResponse();
            questionResultResponse.setQuestion(question);
            questionResultResponse.setReponseCandidats(reponseCandidats);
            questionResultResponses.add(questionResultResponse);
        }
        participationResultResponse.setQuestionResults(questionResultResponses);
        return participationResultResponse;
    }
}
