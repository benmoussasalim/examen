package com.ant.examen.repository;

import com.ant.examen.entities.Question;
import org.springframework.data.jpa.repository.JpaRepository;

import com.ant.examen.entities.Theme;

import java.util.List;

public interface ThemeRepository extends JpaRepository<Theme, Integer> {
    boolean existsByLibelle(String libelle);
    boolean existsByIdAndQuestionsIsNotNull(Integer id);

}
