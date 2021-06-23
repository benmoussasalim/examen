package com.ant.examen.repository;

import com.ant.examen.entities.Participation;
import com.ant.examen.entities.Question;
import com.ant.examen.entities.Theme;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface QuestionRepository extends JpaRepository<Question, Integer> {
    boolean existsByLibelle(String libelle);

    boolean existsByIdAndReponsesIsNotNull(Integer id);

    Question getById(Integer id);

    List<Question> findByTheme(Theme theme);
    Integer countByTheme(Theme theme);

    @Query("select q from Question q where q.theme=:theme ORDER BY RAND()")
    public List<Question> findRandomQuestion(Theme theme, Pageable pageable);

    @Query("select q from Question q inner join q.reponses rep inner join rep.reponseCandidats repCand " +
            "where repCand.participation=:participation Group By  q ORDER BY repCand ")
    List<Question> findQuestionByParticipation(Participation participation);
}
