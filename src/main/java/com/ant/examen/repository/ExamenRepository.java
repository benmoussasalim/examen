package com.ant.examen.repository;

import com.ant.examen.entities.Entreprise;
import com.ant.examen.entities.Examen;
import com.ant.examen.entities.Question;
import com.ant.examen.entities.Theme;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;

public interface ExamenRepository extends JpaRepository<Examen, Integer> {
    boolean existsByIdAndParticipationsIsNotNull(Integer id);
    List<Examen> findByEntreprise(Entreprise entreprise);
    Examen getById(Integer id);
    List<Examen> findByTheme(Theme theme);
    List<Examen> findByDateExpiration(Date dataExpiration);
}