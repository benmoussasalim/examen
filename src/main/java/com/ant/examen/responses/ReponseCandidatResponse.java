package com.ant.examen.responses;

import lombok.Data;


@Data
public class ReponseCandidatResponse {
    private Integer id;
    private boolean statut;

    private ReponseResponse reponse;
}
