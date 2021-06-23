package com.ant.examen.repository;

import com.ant.examen.entities.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface InvitationRepository extends JpaRepository< Invitation, Integer> {
    @Query("select i from Invitation i inner join i.participation p where p.examen.entreprise.id=:id")
    List<Invitation> findByEntreprise(Integer id);
    @Query("select i from Invitation i inner join i.participation p where p.candidat.id=:id")
    List<Invitation> findByCandidat(Integer id);

    Invitation findByParticipation(Participation participation);


    @Query("select count(i) from Invitation i where i.participation.examen.entreprise=:entreprise and i.participation.examen.theme=:theme  ")
    Integer countByThemeAndEntreprise(Theme theme, Entreprise entreprise);

    @Query("select count(i) from Invitation i where i.participation.examen.entreprise=:entreprise and i.etat=:etat  ")
    Integer countByEtatAndEntreprise(boolean etat, Entreprise entreprise);
    @Query("select count(i) from Invitation i where i.participation.candidat=:candidat and i.etat=:etat  ")
    Integer countByEtatAndCandidat(boolean etat, Candidat candidat);
}
