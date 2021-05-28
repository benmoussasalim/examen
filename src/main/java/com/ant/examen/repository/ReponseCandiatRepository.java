package com.ant.examen.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ant.examen.entities.ReponseCandidat;

public interface ReponseCandiatRepository extends JpaRepository<ReponseCandidat, Integer> {
}
