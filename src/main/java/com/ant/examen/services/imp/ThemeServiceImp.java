package com.ant.examen.services.imp;

import com.ant.examen.responses.MessageResponse;
import com.ant.examen.entities.Theme;
import com.ant.examen.repository.ThemeRepository;
import com.ant.examen.services.ThemeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ThemeServiceImp implements ThemeService {
    @Autowired
    private ThemeRepository themeRepository;
    @Override
    public MessageResponse save(Theme theme) {
        boolean exist = themeRepository.existsByLibelle(theme.getLibelle());
        if(exist){
            return  new MessageResponse(false, "Attention", "Théme existe déjà");
        }

        themeRepository.save(theme);

        return new MessageResponse(true, "Succès", "Opération effectuée");
    }

    @Override
    public MessageResponse update(Theme theme) {
        boolean exist = themeRepository.existsByLibelle(theme.getLibelle());
        if(exist) {
            return  new MessageResponse(false, "Attention", "Théme existe déjà");
        }
        themeRepository.save(theme);

        return new MessageResponse(true, "Succès", "Opération effectuée");
    }

    @Override
    public MessageResponse delete(Integer id) {

        boolean exist = themeRepository.existsByIdAndQuestionsIsNotNull(id);
        if(exist){
            return  new MessageResponse(false, "Attention",
                    "Théme affecté a un ou plusieurs Questions");
        }


        themeRepository.deleteById(id);

        return new MessageResponse(true, "Succès", "Opération effectuée");
    }


    @Override
    public List<Theme> findAll() {
        return themeRepository.findAll();
    }
}
