package com.ant.examen.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Data
public class Question implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Lob
    private String libelle;
    private boolean choixMultiple;
    @ManyToOne
    private Theme theme;
    @OneToMany(mappedBy = "question")
    @JsonIgnore
    List<Reponse> reponses;
}
