package com.ant.examen.services.imp;

import com.ant.examen.dto.MessageResponse;
import com.ant.examen.entities.Question;
import com.ant.examen.entities.Reponse;
import com.ant.examen.repository.ReponseRepository;
import com.ant.examen.services.ReponseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReponseServiceImp implements ReponseService {
    @Autowired
    private ReponseRepository reponseRepository;
    @Override
    public MessageResponse save(Reponse reponse) {

        reponseRepository.save(reponse);

        return new MessageResponse(true, "Succès", "Opération effectuée");
    }

    @Override
    public MessageResponse save(List<Reponse> reponses) {

        reponseRepository.saveAll(reponses);

        return new MessageResponse(true, "Succès", "Opération effectuée");
    }

    @Override
    public MessageResponse update(Reponse reponse) {

        reponseRepository.save(reponse);

        return new MessageResponse(true, "Succès", "Opération effectuée");
    }

    @Override
    public MessageResponse delete(Integer id) {

        boolean exist = reponseRepository.existsByIdAndReponseCandidatsIsNotNull(id);
        if(exist){
            return  new MessageResponse(false, "Attention",
                    "Théme affecté a un ou plusieurs Questions");
        }


        reponseRepository.deleteById(id);

        return new MessageResponse(true, "Succès", "Opération effectuée");
    }


    @Override
    public List<Reponse> findAll() {
        return reponseRepository.findAll();
    }

    @Override
    public List<Reponse> findByQuestion(Integer id) {
        Question question = new Question();
        question.setId(id);
        return reponseRepository.findByQuestion(question);
    }
}
