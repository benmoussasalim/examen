package com.ant.examen.services;

import com.ant.examen.entities.Invitation;
import com.ant.examen.responses.MessageResponse;

import java.util.List;

public interface InvitationService {
    public MessageResponse save(Invitation invitation);
    public List<Invitation> findByEntreprise(Integer id);
    public List<Invitation> findByCandidat(Integer id);

}
