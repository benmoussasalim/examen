package com.ant.examen.responses;

import com.ant.examen.entities.Examen;
import lombok.Data;

@Data
public class ExamenResponse {
    private Examen examen;
    private boolean finished;
    private byte[] picture;
}
