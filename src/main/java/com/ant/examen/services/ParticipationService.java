package com.ant.examen.services;

import com.ant.examen.dto.ParticipationResponse;
import com.ant.examen.entities.Participation;

public interface ParticipationService {
    public ParticipationResponse save(Integer idCandidat, Integer idExamen);
}
