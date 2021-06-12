package com.ant.examen.repository;

import com.ant.examen.entities.Candidat;
import org.springframework.data.jpa.repository.JpaRepository;

import com.ant.examen.entities.Participation;
import com.ant.examen.entities.ParticipationId;

import java.util.List;

public interface ParticipationRepository extends JpaRepository<Participation, ParticipationId> {

    List<Participation> findByCandidat(Candidat candidat);
}
