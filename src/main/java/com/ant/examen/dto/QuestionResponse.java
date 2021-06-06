package com.ant.examen.dto;

import com.ant.examen.entities.Question;
import com.ant.examen.entities.ReponseCandidat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.util.List;

@Data
public class QuestionResponse {

    private Question question;

    private List<ReponseCandidatResponse> reponses;
}
