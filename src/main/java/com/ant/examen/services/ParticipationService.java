package com.ant.examen.services;

import com.ant.examen.responses.MessageResponse;
import com.ant.examen.responses.ParticipationResponse;
import com.ant.examen.entities.Participation;

import java.util.List;

public interface ParticipationService {
    public ParticipationResponse save(Integer idCandidat, Integer idExamen);
    public MessageResponse finishParticipation(Participation participation);
    public List<Participation> findByCandidat(Integer id);
    public List<Participation> findAll();
}
