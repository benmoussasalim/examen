package com.ant.examen.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ant.examen.entities.Candidat;
import org.springframework.data.jpa.repository.Query;

public interface CandidatRepository extends JpaRepository<Candidat, Integer> {
    boolean existsByEmail(String email);
    boolean existsByIdAndParticipationsIsNotNull(Integer id);
    Candidat findByEmail(String email);
    boolean existsByEmailAndId(String email, Integer id);

    Integer countByEnabled(boolean b);
}
