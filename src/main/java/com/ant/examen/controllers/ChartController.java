package com.ant.examen.controllers;

import com.ant.examen.responses.ChartResponse;
import com.ant.examen.services.ChartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("*")
@RequestMapping("/chart")
public class ChartController {
    @Autowired
    private ChartService chartService;
    @GetMapping("/entreprise")
    public ChartResponse countByEntreprise(){
        return chartService.countEntrepriseByEtat();
    }
    @GetMapping("/candidat")
    public ChartResponse countByCandidat(){
        return chartService.countCandidatByEtat();
    }
    @GetMapping("/theme")
    public ChartResponse countByTheme(){
        return chartService.countQuestionExamenByTheme();
    }
    @GetMapping("/theme/{id}")
    public ChartResponse countByThemeEntreprise(@PathVariable Integer id){
        return chartService.countExamenParticipationByEntreprise(id);
    }
    @GetMapping("/invitation/{id}")
    public ChartResponse countByInvitationEntreprise(@PathVariable Integer id){
        return chartService.countInvitationByEntreprise(id);
    }
    @GetMapping("/mention/{id}")
    public ChartResponse countByMentionEntreprise(@PathVariable Integer id){
        return chartService.countMentionByEntreprise(id);
    }

    @GetMapping("/invitation/candidat/{id}")
    public ChartResponse countByInvitationCandidat(@PathVariable Integer id){
        return chartService.countInvitationByCandidat(id);
    }
    @GetMapping("/mention/candidat/{id}")
    public ChartResponse countByMentionCandidat(@PathVariable Integer id){
        return chartService.countMentionByCandidat(id);
    }
}
