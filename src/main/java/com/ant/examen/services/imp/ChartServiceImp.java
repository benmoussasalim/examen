package com.ant.examen.services.imp;

import com.ant.examen.entities.Candidat;
import com.ant.examen.entities.Entreprise;
import com.ant.examen.entities.Participation;
import com.ant.examen.entities.Theme;
import com.ant.examen.repository.*;
import com.ant.examen.responses.ChartResponse;
import com.ant.examen.services.ChartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ChartServiceImp implements ChartService {
    @Autowired
    private EntrepriseRepository entrepriseRepository;
    @Autowired
    private ParticipationRepository participationRepository;
    @Autowired
    private InvitationRepository invitationRepository;
    @Autowired
    private ExamenRepository examenRepository;
    @Autowired
    private CandidatRepository candidatRepository;
    @Autowired
    private ThemeRepository themeRepository;
    @Autowired
    private QuestionRepository questionRepository;
    @Override
    public ChartResponse countEntrepriseByEtat() {
        ChartResponse chartResponse = new ChartResponse();
        List<String> labels = new ArrayList<>();
        labels.add("Activé");
        labels.add("Désactivé");

        List<Integer> values = new ArrayList<>();
        values.add(entrepriseRepository.countByEnabled(true));
        values.add(entrepriseRepository.countByEnabled(false));
        chartResponse.setLabels(labels);
        chartResponse.setValues1(values);
        return chartResponse;
    }

    @Override
    public ChartResponse countCandidatByEtat() {
        ChartResponse chartResponse = new ChartResponse();
        List<String> labels = new ArrayList<>();
        List<Integer> values = new ArrayList<>();
        labels.add("Activé");
        labels.add("Désactivé");


        values.add(candidatRepository.countByEnabled(true));
        values.add(candidatRepository.countByEnabled(false));
        chartResponse.setLabels(labels);
        chartResponse.setValues1(values);
        return chartResponse;
    }

    @Override
    public ChartResponse countQuestionExamenByTheme() {
        ChartResponse chartResponse = new ChartResponse();
        List<String> labels = new ArrayList<>();
        List<Integer> values1 = new ArrayList<>();
        List<Integer> values2 = new ArrayList<>();

        for(Theme theme: themeRepository.findAll()) {
            labels.add(theme.getLibelle());

            values1.add(questionRepository.countByTheme(theme));
            values2.add(examenRepository.countByTheme(theme));
        }

        chartResponse.setLabels(labels);
        chartResponse.setValues1(values1);
        chartResponse.setValues2(values2);
        return chartResponse;
    }

    @Override
    public ChartResponse countExamenParticipationByEntreprise(Integer id) {
        ChartResponse chartResponse = new ChartResponse();
        List<String> labels = new ArrayList<>();
        List<Integer> values1 = new ArrayList<>();
        List<Integer> values2 = new ArrayList<>();
        List<Integer> values3 = new ArrayList<>();
        Entreprise entreprise = new Entreprise();
        entreprise.setId(id);
        for(Theme theme: themeRepository.findAll()) {
            labels.add(theme.getLibelle());
            values1.add(examenRepository.countByThemeAndEntreprise(theme, entreprise));
            values2.add(participationRepository.countByThemeAndEntreprise(theme, entreprise));
            values3.add(invitationRepository.countByThemeAndEntreprise(theme, entreprise));

        }

        chartResponse.setLabels(labels);
        chartResponse.setValues1(values1);
        chartResponse.setValues2(values2);
        chartResponse.setValues3(values3);
        return chartResponse;
    }

    @Override
    public ChartResponse countInvitationByEntreprise(Integer id) {
        ChartResponse chartResponse = new ChartResponse();
        List<String> labels = new ArrayList<>();
        List<Integer> values1 = new ArrayList<>();

        Entreprise entreprise = new Entreprise();
        entreprise.setId(id);

         labels.add("Acceptée");
         labels.add("Refusée");
         labels.add("En attente");


         values1.add(invitationRepository.countByEtatAndEntreprise(true, entreprise));
         values1.add(invitationRepository.countByEtatAndEntreprise(false, entreprise));
     List<Participation> list =   participationRepository.countByEntrepriseAndInvitationIsNull(entreprise).
                stream().filter(p -> p.getInvitation() ==null).collect(Collectors.toList());
        values1.add(list.size());

        chartResponse.setLabels(labels);
        chartResponse.setValues1(values1);

        return chartResponse;
    }

    @Override
    public ChartResponse countMentionByEntreprise(Integer id) {

        ChartResponse chartResponse = new ChartResponse();
        List<String> labels = new ArrayList<>();
        List<Integer> values1 = new ArrayList<>();

        Entreprise entreprise = new Entreprise();
        entreprise.setId(id);

        labels.add("Très faible");
        labels.add("Faible");
        labels.add("Passable");
        labels.add("Bien");
        labels.add("Très bien");
        labels.add("Excellent");

        int tresFaible =0, faible = 0, passable =0, bien = 0, tresBien = 0, excelent = 0;


        List<Participation> participations = participationRepository.findByEntreprise(entreprise);

        for(Participation participation: participations) {

            double score = participation.getScore() / participation.getExamen().getNbrQuestion() * 100;

            if(score<25) {
                tresFaible ++;
            } else if (score>= 25 && score <50 ) {
                faible ++;
            }else if (score>= 50 && score <60 ) {
                passable++;
            }else if (score>= 60 && score <70 ) {
                bien++;
            }else if (score>= 70 && score <90 ) {
                tresBien++;
            }else  {
                excelent++;
            }
        }
        values1.add(tresFaible);
        values1.add(faible);
        values1.add(passable);
        values1.add(bien);
        values1.add(tresBien);
        values1.add(excelent);


        chartResponse.setLabels(labels);
        chartResponse.setValues1(values1);

        return chartResponse;
    }

    @Override
    public ChartResponse countInvitationByCandidat(Integer id) {
        ChartResponse chartResponse = new ChartResponse();
        List<String> labels = new ArrayList<>();
        List<Integer> values1 = new ArrayList<>();

        Candidat candidat = new Candidat();
        candidat.setId(id);

        labels.add("Acceptée");
        labels.add("Refusée");
        labels.add("En attente");


        values1.add(invitationRepository.countByEtatAndCandidat(true, candidat));
        values1.add(invitationRepository.countByEtatAndCandidat(false, candidat));
        List<Participation> list =   participationRepository.countByCandidatAndInvitationIsNull(candidat).
                stream().filter(p -> p.getInvitation() ==null).collect(Collectors.toList());
        values1.add(list.size());

        chartResponse.setLabels(labels);
        chartResponse.setValues1(values1);

        return chartResponse;
    }

    @Override
    public ChartResponse countMentionByCandidat(Integer id) {
        ChartResponse chartResponse = new ChartResponse();
        List<String> labels = new ArrayList<>();
        List<Integer> values1 = new ArrayList<>();

        Candidat candidat = new Candidat();
        candidat.setId(id);

        labels.add("Très faible");
        labels.add("Faible");
        labels.add("Passable");
        labels.add("Bien");
        labels.add("Très bien");
        labels.add("Excellent");

        int tresFaible =0, faible = 0, passable =0, bien = 0, tresBien = 0, excelent = 0;


        List<Participation> participations = participationRepository.findByCandidat(candidat);

        for(Participation participation: participations) {

            double score = participation.getScore() / participation.getExamen().getNbrQuestion() * 100;

            if(score<25) {
                tresFaible ++;
            } else if (score>= 25 && score <50 ) {
                faible ++;
            }else if (score>= 50 && score <60 ) {
                passable++;
            }else if (score>= 60 && score <70 ) {
                bien++;
            }else if (score>= 70 && score <90 ) {
                tresBien++;
            }else  {
                excelent++;
            }
        }
        values1.add(tresFaible);
        values1.add(faible);
        values1.add(passable);
        values1.add(bien);
        values1.add(tresBien);
        values1.add(excelent);


        chartResponse.setLabels(labels);
        chartResponse.setValues1(values1);

        return chartResponse;
    }
}
