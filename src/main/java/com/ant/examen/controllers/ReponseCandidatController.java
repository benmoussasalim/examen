package com.ant.examen.controllers;

import com.ant.examen.responses.MessageResponse;
import com.ant.examen.responses.ParticipationResultResponse;
import com.ant.examen.responses.QuestionResultResponse;
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

    @GetMapping("/{idExamen}/{idCandidat}")
    public ParticipationResultResponse findByParticipation(@PathVariable Integer idExamen, @PathVariable Integer idCandidat) {
        return  reponseCandidatService.findByParticipation(idExamen, idCandidat);
    }
}
