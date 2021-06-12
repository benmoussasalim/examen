package com.ant.examen.dto;

import com.ant.examen.entities.Entreprise;
import com.ant.examen.entities.Theme;
import lombok.Data;

@Data
public class FilterRequest {
    private Integer idCandidat;
    private Theme theme;
    private Entreprise entreprise;
    private String libelle;
}
