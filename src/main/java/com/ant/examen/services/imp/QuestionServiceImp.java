package com.ant.examen.services.imp;

import com.ant.examen.responses.MessageResponse;
import com.ant.examen.entities.Question;
import com.ant.examen.entities.Theme;
import com.ant.examen.repository.QuestionRepository;
import com.ant.examen.repository.ThemeRepository;
import com.ant.examen.services.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuestionServiceImp implements QuestionService {
    @Autowired
    private QuestionRepository questionRepository;
    @Autowired
    private ThemeRepository themeRepository;
    @Override
    public MessageResponse save(Question question) {


        questionRepository.save(question);

        return new MessageResponse(true, "Succès", "Opération effectuée");
    }

    @Override
    public MessageResponse update(Question question) {

        questionRepository.save(question);

        return new MessageResponse(true, "Succès", "Opération effectuée");
    }

    @Override
    public MessageResponse delete(Integer id) {

        boolean exist = questionRepository.existsByIdAndReponsesIsNotNull(id);
        if(exist){
            return  new MessageResponse(false, "Attention",
                    "Théme affecté a un ou plusieurs Questions");
        }


        questionRepository.deleteById(id);

        return new MessageResponse(true, "Succès", "Opération effectuée");
    }


    @Override
    public List<Question> findAll() {
        return questionRepository.findAll();
    }

    @Override
    public Question findById(Integer id) {
        return questionRepository.getById(id);
    }

    @Override
    public List<Question> findByTheme(Integer id) {
        Theme theme = new Theme();
        theme.setId(id);
        return questionRepository.findByTheme(theme);
    }
}
