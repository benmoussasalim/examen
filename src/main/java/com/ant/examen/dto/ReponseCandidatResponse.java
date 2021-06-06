package com.ant.examen.dto;

import com.ant.examen.entities.Reponse;
import lombok.Data;


@Data
public class ReponseCandidatResponse {
    private Integer id;
    private boolean statut;

    private ReponseResponse reponse;
}
