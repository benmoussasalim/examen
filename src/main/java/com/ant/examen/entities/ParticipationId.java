package com.ant.examen.entities;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
@Data
public class ParticipationId implements Serializable {
    @Column(name="candidat_id")
    private Integer candidatId;
    @Column(name="examen_id")
    private Integer examenId;
}
