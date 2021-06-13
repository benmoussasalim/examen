package com.ant.examen.responses;

import com.ant.examen.entities.Participation;
import com.ant.examen.entities.Question;
import com.ant.examen.entities.ReponseCandidat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.util.List;

@Data
public class QuestionResultResponse {
    private Question question;
    private List<ReponseCandidat> reponseCandidats;
}
