package com.ant.examen.services.imp;

import com.ant.examen.dto.MessageResponse;
import com.ant.examen.entities.Candidat;
import com.ant.examen.entities.Entreprise;
import com.ant.examen.listners.OnRegistrationCompleteEvent;
import com.ant.examen.repository.EntrepriseRepository;
import com.ant.examen.repository.UserRepository;
import com.ant.examen.services.EntrepriseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class EntrepriseServiceImp implements EntrepriseService {
    @Autowired
    private EntrepriseRepository entrepriseRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private ApplicationEventPublisher eventPublisher;
    @Override
    public MessageResponse register(Entreprise entreprise) {
        boolean exist = userRepository.existsByEmail(entreprise.getEmail());
        if (exist) {
            return new MessageResponse(false, "Attention", "Email existe déjà");
        }
        String encodedPassword= passwordEncoder.encode(entreprise.getPassword());
        entreprise.setPassword(encodedPassword);
        entrepriseRepository.save(entreprise);
        eventPublisher.publishEvent(new OnRegistrationCompleteEvent(entreprise));
        return new MessageResponse(true, "Succès", "Opération effectuée");
    }

    @Override
    public MessageResponse update(Entreprise entreprise) {
        boolean  exist = entrepriseRepository.existsByEmailAndId(entreprise.getEmail(), entreprise.getId());
        if (!exist) {
            exist = entrepriseRepository.existsByEmail(entreprise.getEmail());
            if (exist) {
                return new MessageResponse(false, "Attention", "Email existe déjà");
            }
        }
            entrepriseRepository.save(entreprise);

               return new MessageResponse(true, "Succès", "Opération effectuée");
    }

    @Override
    public MessageResponse delete(Integer id) {
            boolean exist = entrepriseRepository.existsByIdAndExamensIsNotNull(id);
            if (exist) {
                return new MessageResponse(false, "Attention",
                        "Candidat affecté a un ou plusieurs examens");
            }


            entrepriseRepository.deleteById(id);

            return new MessageResponse(true, "Succès", "Opération effectuée");
    }

    @Override
    public MessageResponse changeState(Entreprise entreprise) {
        entrepriseRepository.save(entreprise);

        return new MessageResponse(true, "Succès", "Opération effectuée");
    }

    @Override
    public List<Entreprise> findAll() {
        return entrepriseRepository.findAll();
    }

    @Override
    public Entreprise findByEmail(String email) {
        return entrepriseRepository.findByEmail(email);
    }

    @Override
    public Entreprise findById(Integer id) {
        return entrepriseRepository.findById(id).orElse(null);
    }
}
