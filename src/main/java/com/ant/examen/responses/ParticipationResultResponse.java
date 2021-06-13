package com.ant.examen.responses;

import com.ant.examen.entities.Participation;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.util.List;

@Data
public class ParticipationResultResponse {
    @JsonIgnoreProperties("invitation")
    private Participation participation;
    private List<QuestionResultResponse> questionResults;
}
