package com.ant.examen.entities;


import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Entity
@Data
public class Participation implements Serializable {
    @EmbeddedId
    private ParticipationId id;
   
    private Date dateParticipation;
    private boolean fini;
    private double score;
    
    @ManyToOne
    @JoinColumn(name = "candidat_id", insertable = false, updatable = false)
    private Candidat candidat;
    @ManyToOne
    @JoinColumn(name = "examen_id", insertable = false, updatable = false)
    private Examen examen;
    @OneToOne
    private Invitation invitation;
    @OneToMany(mappedBy = "participation")
    @JsonIgnore
    private List<ReponseCandidat> reponseCandidats;
}
