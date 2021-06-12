package com.ant.examen.repository;

import com.ant.examen.entities.Participation;
import com.ant.examen.entities.Question;
import org.springframework.data.jpa.repository.JpaRepository;

import com.ant.examen.entities.ReponseCandidat;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ReponseCandidatRepository extends JpaRepository<ReponseCandidat, Integer> {
    @Query("select rc from ReponseCandidat rc inner join rc.reponse rp where rp.question=:question " +
            "and rc.participation=:participation")
    List<ReponseCandidat> findByQuestion(Question question, Participation participation);
  /*  @Query("select rc from ReponseCandidat rc inner join rc.reponse rp where rp.question=:question ")
    List<ReponseCandidat> findByQuestion(Question question);*/
    List<ReponseCandidat> findByParticipation(Participation participation);
}
