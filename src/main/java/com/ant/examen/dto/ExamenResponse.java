package com.ant.examen.dto;

import com.ant.examen.entities.Examen;
import lombok.Data;

@Data
public class ExamenResponse {
    private Examen examen;
    private byte[] picture;
}
