package com.ant.examen.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ant.examen.entities.Participation;
import com.ant.examen.entities.ParticipationId;

public interface ParticipationRepository extends JpaRepository<Participation, ParticipationId> {
}
