package com.ant.examen.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Data
public class Invitation implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Date dateInvitation;
    private boolean etat;
    private String roomName;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @OneToOne
    private Participation participation;
}
