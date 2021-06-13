package com.ant.examen.services;

import com.ant.examen.responses.MessageResponse;
import com.ant.examen.entities.Candidat;

import java.util.List;

public interface CandidatService {
    public MessageResponse register(Candidat candidat);
    public MessageResponse update(Candidat candidat);
    public MessageResponse delete(Integer id);
    public MessageResponse changeState(Candidat candidat);
    public List<Candidat> findAll();
    public Candidat findById(Integer id);
    public Candidat findByEmail(String email);

}
