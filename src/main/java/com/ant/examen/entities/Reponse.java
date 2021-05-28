package com.ant.examen.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Data
public class Reponse implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String libelle;
    private boolean correct;
    @ManyToOne
    private Question question;
    @OneToMany(mappedBy = "reponse")
    @JsonIgnore
    private List<ReponseCandidat> reponseCandidats;
}
