package com.ant.examen.entities;

import java.io.Serializable;

import javax.persistence.*;

import lombok.Data;

@Entity
@Data
public class ReponseCandidat implements Serializable {
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private boolean statut;
    @ManyToOne
    private Reponse reponse;
    @ManyToOne
    private Participation participation;
}
