package com.ant.examen.controllers;

import com.ant.examen.dto.MessageResponse;
import com.ant.examen.entities.Examen;
import com.ant.examen.entities.Question;
import com.ant.examen.services.ExamenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
@CrossOrigin("*")
@RestController
@RequestMapping("/examens")
public class ExamenController {
    @Autowired
    private ExamenService examenService;
   // @PreAuthorize("hasAnyRole('COMPANY', 'CANDIDAT')")
    @GetMapping
    public List<Examen> findAll(){
        return examenService.findAll();
    }
   // @PreAuthorize("hasAnyRole('COMPANY', 'CANDIDAT')")
    @GetMapping("/examenbyentreprise/{id}")
    public List<Examen> findByEntreprise(@PathVariable ("id") Integer id){
        return examenService.findByEntreprise(id);
    }
    @GetMapping("/examenbydateexpiration/{date}")
    public List<Examen> findByDateExpiration(@RequestParam ("date") Date date){
        return examenService.findByDateExpiration(date);
    }
    @GetMapping("/examenbyid/{id}")
    public Examen findById(@PathVariable ("id") Integer id){ return examenService.findById(id); }
   // @PreAuthorize("hasRole('COMPANY')")
    @PostMapping
    public MessageResponse save (@RequestBody Examen examen){
        return examenService.save(examen);
    }
   // @PreAuthorize("hasRole('COMPANY')")
    @PutMapping
    public MessageResponse update(@RequestBody Examen examen)
    {
        return  examenService.update(examen);
    }
  //  @PreAuthorize("hasRole('COMPANY')")
    @DeleteMapping("/{id}")
    public MessageResponse delete(@PathVariable("id") Integer id)
    {
        return examenService.delete(id);
    }
    @GetMapping("/examenbytheme/{id}")
    public List<Examen> findByTheme(@PathVariable Integer id){
        return examenService.findByTheme(id);
    }
}


