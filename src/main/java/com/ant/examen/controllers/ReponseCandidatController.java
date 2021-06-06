package com.ant.examen.controllers;

import com.ant.examen.dto.MessageResponse;
import com.ant.examen.entities.Reponse;
import com.ant.examen.entities.ReponseCandidat;
import com.ant.examen.services.ReponseCandidatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/reponseCandidat")
@CrossOrigin("*")
public class ReponseCandidatController {
    @Autowired
    private ReponseCandidatService reponseCandidatService;

    @PostMapping
    public MessageResponse save(@RequestBody List<ReponseCandidat> reponseCandidats) {
        return reponseCandidatService.save(reponseCandidats);
    }
}
