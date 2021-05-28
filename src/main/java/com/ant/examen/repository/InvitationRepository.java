package com.ant.examen.repository;

import com.ant.examen.entities.Invitation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InvitationRepository extends JpaRepository< Invitation, Integer> {
}
