package com.ant.examen.services;

import com.ant.examen.dto.MessageResponse;
import com.ant.examen.entities.ReponseCandidat;

import java.util.List;

public interface ReponseCandidatService {
    public MessageResponse save (List<ReponseCandidat> reponseCandidats);
}
