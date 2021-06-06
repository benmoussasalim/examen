package com.ant.examen.dto;

import com.ant.examen.entities.Participation;
import com.ant.examen.entities.Question;
import lombok.Data;

import java.util.List;

@Data
public class ParticipationResponse {
    private Participation participation;
    private List<QuestionResponse> questions;

}
