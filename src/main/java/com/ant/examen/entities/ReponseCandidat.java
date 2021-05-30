package com.ant.examen.entities;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import lombok.Data;

@Entity
@Data
public class ReponseCandidat implements Serializable {
   @Id
    private Integer id;
    private Boolean statut;
    @ManyToOne
    private Reponse reponse;
    @ManyToOne
    private Participation participation;
}
