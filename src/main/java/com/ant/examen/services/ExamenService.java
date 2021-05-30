package com.ant.examen.services;

import com.ant.examen.dto.ExamenResponse;
import com.ant.examen.dto.MessageResponse;
import com.ant.examen.entities.Examen;
import com.ant.examen.entities.Question;

import java.util.Date;
import java.util.List;

public interface ExamenService {
        public MessageResponse save(Examen examen);
        public MessageResponse delete(Integer id);
        public MessageResponse update(Examen examen);
        public List<Examen> findAll();
        public List<Examen> findByEntreprise(Integer id);
        public Examen findById(Integer id);
        public List<Examen> findByTheme(Integer id);
        public List<Examen> findByDateExpiration(Date dataExpiration);
        public List<ExamenResponse> findNotExpired();
}
