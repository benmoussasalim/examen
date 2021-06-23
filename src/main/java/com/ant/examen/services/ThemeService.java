package com.ant.examen.services;

import com.ant.examen.entities.Theme;
import com.ant.examen.responses.MessageResponse;

import java.util.List;

public interface ThemeService {
    public MessageResponse save(Theme theme);
    public MessageResponse update(Theme theme);
    public MessageResponse delete(Integer id);
    public List<Theme> findAll();
}
