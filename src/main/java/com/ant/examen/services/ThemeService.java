package com.ant.examen.services;

import com.ant.examen.dto.MessageResponse;
import com.ant.examen.entities.Theme;

import java.util.List;

public interface ThemeService {
    public MessageResponse save(Theme theme);
    public MessageResponse update(Theme theme);
    public MessageResponse delete(Integer id);
    public List<Theme> findAll();
}
