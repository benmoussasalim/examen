package com.ant.examen.controllers;

import com.ant.examen.dto.MessageResponse;
import com.ant.examen.entities.Candidat;
import com.ant.examen.entities.Entreprise;
import com.ant.examen.services.CandidatService;
import com.ant.examen.services.EntrepriseService;
import com.ant.examen.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("*")
@RestController
@RequestMapping("/register")
public class RegisterController {
    @Autowired
    private CandidatService candidatService;
    @Autowired
    private EntrepriseService entrepriseService;
    @Autowired
    private UserService userService;

    @PostMapping("/candidat")
    public MessageResponse registerCandidat(@RequestBody Candidat candidat){
        return candidatService.register(candidat);
    }
    @PostMapping("/entreprise")
    public MessageResponse registerEntreprise(@RequestBody Entreprise entreprise){
        return entrepriseService.register(entreprise);
    }
    @GetMapping("/confirm")
    public MessageResponse confirm(@RequestParam ("token") String token){
        return userService.activateAccount(token);
    }
}
