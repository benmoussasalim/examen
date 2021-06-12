package com.ant.examen.controllers;

import com.ant.examen.dto.MessageResponse;
import com.ant.examen.dto.ParticipationResponse;
import com.ant.examen.entities.Participation;
import com.ant.examen.entities.Question;
import com.ant.examen.services.ParticipationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/participation")
@CrossOrigin("*")
public class ParticipationController {
    @Autowired
    private ParticipationService participationService;

    @GetMapping("/{idExamen}/{idCandidat}")
    public ParticipationResponse save(@PathVariable Integer idExamen, @PathVariable Integer idCandidat) {
        return participationService.save(idCandidat, idExamen);
    }

    @PatchMapping("/finish")
    public MessageResponse finishParticipation(@RequestBody Participation participation) {
      return   participationService.finishParticipation(participation);
    }

    @GetMapping("/participationbycandidat/{id}")
    public List<Participation> findByCandidat(@PathVariable Integer id){
        return participationService.findByCandidat(id);
    }
}
