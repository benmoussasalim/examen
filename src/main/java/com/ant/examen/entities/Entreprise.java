package com.ant.examen.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.List;

@Entity
@Data
public class Entreprise extends User {
    private String raisonSociale;
    private String matriculeFiscale;
    @OneToMany(mappedBy = "entreprise")
    @JsonIgnore
    List<Examen> examens;
}
