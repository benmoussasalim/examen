package com.ant.examen.responses;

import com.ant.examen.entities.Participation;
import lombok.Data;

import java.util.List;

@Data
public class ParticipationResultResponse {

    private Participation participation;
    private List<QuestionResultResponse> questionResults;
}
