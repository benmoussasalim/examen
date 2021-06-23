package com.ant.examen.responses;

import com.ant.examen.entities.Question;
import com.ant.examen.entities.ReponseCandidat;
import lombok.Data;

import java.util.List;

@Data
public class QuestionResultResponse {
    private Question question;
    private List<ReponseCandidat> reponseCandidats;
}
