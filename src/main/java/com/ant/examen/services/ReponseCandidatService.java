package com.ant.examen.services;

import com.ant.examen.responses.MessageResponse;
import com.ant.examen.responses.ParticipationResultResponse;
import com.ant.examen.entities.ReponseCandidat;

import java.util.List;

public interface ReponseCandidatService {
    public MessageResponse save (List<ReponseCandidat> reponseCandidats);
    public ParticipationResultResponse findByParticipation(Integer idExamen, Integer idCandidat);
}
