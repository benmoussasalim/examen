package com.ant.examen;

import com.ant.examen.entities.Entreprise;
import com.ant.examen.entities.Participation;
import com.ant.examen.entities.ParticipationId;
import com.ant.examen.repository.ParticipationRepository;
import com.ant.examen.repository.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.data.domain.PageRequest;

@SpringBootApplication
public class ExamenApplication implements CommandLineRunner {
    @Autowired
    private ParticipationRepository participationRepository;
    public static void main(String[] args) {
        SpringApplication.run(ExamenApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
      /*  Participation participation = new Participation();
        ParticipationId id = new ParticipationId();
        id.setCandidatId(1);
        id.setExamenId(3);
        participation.setId(id);
        questionRepository.findQuestionByParticipation(participation)
                .forEach(q-> System.out.println(q.getLibelle()));*/
      /*  Entreprise entreprise = new Entreprise();
        entreprise.setId(3);
        participationRepository.countByEntrepriseAndInvitationIsNull(entreprise).forEach(p ->
                System.out.println( p.getInvitation().getId())
                );*/
    }
}
