package com.ant.examen.controllers;

import com.ant.examen.dto.MessageResponse;
import com.ant.examen.entities.Question;
import com.ant.examen.entities.Theme;
import com.ant.examen.services.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin("*")
@RestController
@RequestMapping("/questions")
public class QuestionController {
    @Autowired
    private QuestionService questionService;
    @GetMapping
    public List<Question> findAll(){
        return questionService.findAll();
    }
   // @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public MessageResponse save (@RequestBody Question question){
        return questionService.save(question);
    }
    //@PreAuthorize("hasRole('ADMIN')")
    @PutMapping
    public MessageResponse update(@RequestBody Question question)
    {
        return  questionService.update(question);
    }
   // @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public MessageResponse delete(@PathVariable("id") Integer id)
    {
        return questionService.delete(id);
    }
    @GetMapping("/questionbyid/{id}")
    public Question findById(@PathVariable ("id") Integer id){ return questionService.findById(id); }
    @GetMapping("/questionbytheme/{id}")
    public List<Question> findByTheme(@PathVariable Integer id){
        return questionService.findByTheme(id);
    }

    }


