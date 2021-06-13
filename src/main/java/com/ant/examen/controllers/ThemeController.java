package com.ant.examen.controllers;

import com.ant.examen.responses.MessageResponse;
import com.ant.examen.entities.Theme;
import com.ant.examen.services.ThemeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin("*")
@RestController
@RequestMapping("/themes")
public class ThemeController {
    @Autowired
    private ThemeService themeService;
    @GetMapping
    public List<Theme> findAll(){
        return themeService.findAll();
    }
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public MessageResponse save (@RequestBody Theme theme){
        return themeService.save(theme);
    }
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping
    public MessageResponse update(@RequestBody Theme theme)
    {
        return  themeService.update(theme);
    }
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public MessageResponse delete(@PathVariable("id") Integer id)
    {
        return themeService.delete(id);
    }

    }


