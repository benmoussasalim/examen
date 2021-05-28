package com.ant.examen.repository;

import com.ant.examen.entities.Question;
import com.ant.examen.entities.Theme;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface QuestionRepository extends JpaRepository<Question, Integer> {
    boolean existsByLibelle(String libelle);
    boolean existsByIdAndReponsesIsNotNull(Integer id);
    Question getById(Integer id);
    List<Question> findByTheme(Theme theme);
}
