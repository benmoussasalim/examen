package com.ant.examen.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ant.examen.entities.Entreprise;

public interface EntrepriseRepository extends JpaRepository<Entreprise, Integer> {
    boolean existsByEmail(String email);
    boolean existsByEmailAndId(String email, Integer id);
    boolean existsByIdAndExamensIsNotNull(Integer id);
    Entreprise findByEmail(String email);
}
