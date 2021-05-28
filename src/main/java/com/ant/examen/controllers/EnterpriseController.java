package com.ant.examen.controllers;

import com.ant.examen.dto.MessageResponse;
import com.ant.examen.entities.Candidat;
import com.ant.examen.entities.Entreprise;
import com.ant.examen.services.EntrepriseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin("*")
@RestController
@RequestMapping("/entreprises")

public class EnterpriseController {
    @Autowired
    private EntrepriseService entrepriseService;
    @GetMapping
    public List<Entreprise> findAll(){
        return entrepriseService.findAll();
    }
    @PatchMapping
    public MessageResponse changeState(@RequestBody Entreprise entreprise)
    {
        return  entrepriseService.changeState(entreprise);
    }
    @PutMapping
    public MessageResponse update(@RequestBody Entreprise entreprise)
    {
        return  entrepriseService.update(entreprise);
    }
    @DeleteMapping("/{id}")
    public MessageResponse delete(@PathVariable("id") Integer id)
    {
        return entrepriseService.delete(id);
    }
    @GetMapping("/email/{email}")
    public Entreprise findByEmail(@PathVariable String email)
    {
        return entrepriseService.findByEmail(email);
    }
    @GetMapping("/{id}")
    public Entreprise findById(@PathVariable Integer id)
    {
        return entrepriseService.findById(id);
    }
    }


