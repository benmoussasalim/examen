package com.ant.examen.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;


import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.List;

@Entity
@Data
public class Candidat extends User {
    private String nom;
    private String prenom;
    @OneToMany(mappedBy ="candidat")
    @JsonIgnore
    private List<Participation> participations;

}
