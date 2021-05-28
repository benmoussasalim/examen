package com.ant.examen.services.imp;

import com.ant.examen.dto.MessageResponse;
import com.ant.examen.entities.Entreprise;
import com.ant.examen.entities.Examen;
import com.ant.examen.entities.Question;
import com.ant.examen.entities.Theme;
import com.ant.examen.repository.ExamenRepository;
import com.ant.examen.services.ExamenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
@Service
public class ExamenServiceImp implements ExamenService {

    @Autowired
    private ExamenRepository examenRepository;
    @Override
    public MessageResponse save(Examen examen) {
        examen.setDateCreation(new Date());
        examenRepository.save(examen);
        return new MessageResponse(true, "Succès", "Opération effectuée");
    }

    @Override
    public MessageResponse delete(Integer id) {
        boolean exist = examenRepository.
                existsByIdAndParticipationsIsNotNull(id);
        if (exist) {
            return new MessageResponse(false, "Attention",
                    "Candidat affecté a un ou plusieurs formations");
        }


        examenRepository.deleteById(id);

        return new MessageResponse(true, "Succès", "Opération effectuée");
    }

    @Override
    public MessageResponse update(Examen examen) {
        boolean exist = examenRepository.existsByIdAndParticipationsIsNotNull(examen.getId());
        if (exist) {
            return new MessageResponse(false, "Attention",
                    "Candidat affecté a un ou plusieurs formations");
        }


        examenRepository.save(examen);

        return new MessageResponse(true, "Succès", "Opération effectuée");
    }

    @Override
    public List<Examen> findAll() {
        return examenRepository.findAll();
    }

    @Override
    public List<Examen> findByEntreprise(Integer id) {
        Entreprise entreprise = new Entreprise();
        entreprise.setId(id);
        return examenRepository.findByEntreprise(entreprise);
    }

    @Override
    public Examen findById(Integer id) {
        return examenRepository.getById(id);
    }

    @Override
    public List<Examen> findByTheme(Integer id) {
        Theme theme = new Theme();
        theme.setId(id);
        return examenRepository.findByTheme(theme);
    }

    @Override
    public List<Examen> findByDateExpiration(Date dataExpiration) {
        return examenRepository.findByDateExpiration(dataExpiration);
    }
}

