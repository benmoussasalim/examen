package com.ant.examen.repository;

import com.ant.examen.entities.Entreprise;
import com.ant.examen.entities.Examen;
import com.ant.examen.entities.Question;
import com.ant.examen.entities.Theme;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Date;
import java.util.List;

public interface ExamenRepository extends JpaRepository<Examen, Integer> {
    boolean existsByIdAndParticipationsIsNotNull(Integer id);
    List<Examen> findByEntreprise(Entreprise entreprise);
    Examen getById(Integer id);
    List<Examen> findByTheme(Theme theme);
    List<Examen> findByDateExpiration(Date dataExpiration);

    List<Examen> findAll(Specification<Examen> specification);


    //  List<Examen> findByDateExpirationGreaterThanEqual(Specification<Examen> specification, Date date);
}
