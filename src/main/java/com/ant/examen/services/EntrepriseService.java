package com.ant.examen.services;

import com.ant.examen.dto.MessageResponse;
import com.ant.examen.entities.Candidat;
import com.ant.examen.entities.Entreprise;

import java.util.List;

public interface EntrepriseService {
    public MessageResponse register(Entreprise entreprise);
    public MessageResponse update(Entreprise entreprise);
    public MessageResponse delete(Integer id);
    public MessageResponse changeState(Entreprise entreprise);
    public List<Entreprise> findAll();
    public Entreprise findByEmail(String email);
    Entreprise findById(Integer id);
}
