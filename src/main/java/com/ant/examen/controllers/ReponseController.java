package com.ant.examen.controllers;

import com.ant.examen.dto.MessageResponse;
import com.ant.examen.entities.Reponse;
import com.ant.examen.services.ReponseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin("*")
@RestController
@RequestMapping("/reponses")
public class ReponseController {
    @Autowired
    private ReponseService reponseService;
    @GetMapping
    public List<Reponse> findAll(){
        return reponseService.findAll();
    }
    @GetMapping ("/question/{id}")
    public List<Reponse> findByQuestion(@PathVariable Integer id){
        return reponseService.findByQuestion(id);
    }
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public MessageResponse save (@RequestBody List<Reponse> reponses){
        return reponseService.save(reponses);
    }
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping
    public MessageResponse update(@RequestBody Reponse reponse)
    {
        return  reponseService.update(reponse);
    }
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public MessageResponse delete(@PathVariable("id") Integer id)
    {
        return reponseService.delete(id);
    }

    }


