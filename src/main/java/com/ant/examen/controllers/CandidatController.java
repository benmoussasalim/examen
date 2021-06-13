package com.ant.examen.controllers;

import com.ant.examen.responses.MessageResponse;
import com.ant.examen.entities.Candidat;
import com.ant.examen.services.CandidatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin("*")
@RestController
@RequestMapping("/candidats")
public class CandidatController {
    @Autowired
    private CandidatService candidatService;
    @GetMapping
    public List<Candidat> findAll(){
        return candidatService.findAll();
    }
    @PutMapping
    public MessageResponse update(@RequestBody Candidat candidat)
    {
        return  candidatService.update(candidat);
    }
    @PreAuthorize("hasRole('ADMIN')")
    @PatchMapping
    public MessageResponse changeState(@RequestBody Candidat candidat)
    {
        return  candidatService.changeState(candidat);
    }
    @DeleteMapping("/{id}")
    public MessageResponse delete(@PathVariable("id") Integer id)
    {
        return candidatService.delete(id);
    }
    @GetMapping("/email/{email}")
    public Candidat findByEmail(@PathVariable String email)
    {
        return candidatService.findByEmail(email);
    }
    @GetMapping("/{id}")
    public Candidat findById(@PathVariable Integer id)
    {
        return candidatService.findById(id);
    }
    }


