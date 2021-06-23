package com.ant.examen.repository;

import com.ant.examen.entities.*;
import io.swagger.models.auth.In;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.jpa.repository.Query;

import java.util.Arrays;
import java.util.List;

public interface ParticipationRepository extends JpaRepository<Participation, ParticipationId> {

    List<Participation> findByCandidat(Candidat candidat);
    @Query("select count(p) from Participation p where p.examen.entreprise=:entreprise and p.examen.theme=:theme  ")
    Integer countByThemeAndEntreprise(Theme theme, Entreprise entreprise);
    @Query("select p from Participation p where p.examen.entreprise=:entreprise  ")
    List<Participation> countByEntrepriseAndInvitationIsNull(Entreprise entreprise);
    @Query("select p from Participation p where p.examen.entreprise=:entreprise")
    List<Participation> findByEntreprise(Entreprise entreprise);
    @Query("select p from Participation p where p.candidat=:candidat")
    List<Participation> countByCandidatAndInvitationIsNull(Candidat candidat);
}
