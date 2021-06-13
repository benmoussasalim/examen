package com.ant.examen.responses;

import com.ant.examen.entities.Question;
import lombok.Data;

import java.util.List;

@Data
public class QuestionResponse {

    private Question question;

    private List<ReponseCandidatResponse> reponses;
}
