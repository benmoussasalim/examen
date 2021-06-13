package com.ant.examen.responses;

import com.ant.examen.entities.Participation;
import lombok.Data;

import java.util.List;

@Data
public class ParticipationResponse {
    private Participation participation;
    private List<QuestionResponse> questions;

}
