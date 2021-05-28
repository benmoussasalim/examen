package com.ant.examen.services;

import com.ant.examen.dto.MessageResponse;
import com.ant.examen.entities.Question;
import com.ant.examen.entities.Theme;

import java.util.List;

public interface QuestionService {
    public MessageResponse save(Question question);
    public MessageResponse update(Question question);
    public MessageResponse delete(Integer id);
    public List<Question> findAll();
    public Question findById(Integer id);
    public List<Question> findByTheme(Integer id);
}
