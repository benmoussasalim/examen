package com.ant.examen.repository;

import com.ant.examen.entities.Question;
import com.ant.examen.entities.Reponse;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReponseRepository extends JpaRepository<Reponse, Integer> {
    boolean existsByLibelle(String libelle);
    boolean existsByIdAndReponseCandidatsIsNotNull(Integer id);
    List<Reponse> findByQuestion(Question question);
}
