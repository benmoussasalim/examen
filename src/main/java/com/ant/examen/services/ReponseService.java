package com.ant.examen.services;

import com.ant.examen.responses.MessageResponse;
import com.ant.examen.entities.Reponse;

import java.util.List;

public interface ReponseService {
    public MessageResponse save(Reponse reponse);
    public MessageResponse update(Reponse reponse);
    public MessageResponse delete(Integer id);
    public List<Reponse> findAll();
    public List<Reponse> findByQuestion(Integer id);

    MessageResponse save(List<Reponse> reponses);
}
