package com.ant.examen.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Entity
@Data
public class Examen implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Temporal(TemporalType.DATE)
    private Date dateCreation;
    @Temporal(TemporalType.DATE)
    private Date dateExpiration;
    private int duree;
    private String libelle;
    private int nbrQuestion;
    @ManyToOne
    private Entreprise entreprise;
    @ManyToOne
    private Theme theme;
    @OneToMany(mappedBy = "examen")
    @JsonIgnore
    private List<Participation> participations;


}
