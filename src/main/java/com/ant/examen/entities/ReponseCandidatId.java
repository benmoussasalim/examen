package com.ant.examen.entities;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Data
@Embeddable
public class ReponseCandidatId implements Serializable {
    @Column(name="reponse_id")
    private Integer reponseId;
    @Column(name="participation_id")
    private Integer participationId;
}
