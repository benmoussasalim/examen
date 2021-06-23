package com.ant.examen.controllers;

import com.ant.examen.entities.Invitation;
import com.ant.examen.responses.MessageResponse;
import com.ant.examen.services.InvitationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/invitation")
public class InvitationController {
    @Autowired
    private InvitationService invitationService;

    @PostMapping
    public MessageResponse save(@RequestBody Invitation invitation) {
        return invitationService.save(invitation);
    }

    @GetMapping("/candidat/{id}")
    public List<Invitation> findByCandidat(@PathVariable Integer id) {
        return invitationService.findByCandidat(id);
    }

    @GetMapping("/entreprise/{id}")
    public List<Invitation> findByEntreprise(@PathVariable Integer id) {
        return invitationService.findByEntreprise(id);
    }
}
