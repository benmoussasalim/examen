package com.ant.examen.services.imp;

import com.ant.examen.dto.MessageResponse;
import com.ant.examen.entities.Candidat;
import com.ant.examen.listners.OnRegistrationCompleteEvent;
import com.ant.examen.repository.CandidatRepository;
import com.ant.examen.repository.UserRepository;
import com.ant.examen.services.CandidatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class CandidatServiceImp implements CandidatService {
    @Autowired
    private CandidatRepository candidatRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private ApplicationEventPublisher eventPublisher;
    @Override
    public MessageResponse register(Candidat candidat) {
       boolean exist = userRepository.existsByEmail(candidat.getEmail());
        if (exist) {
            return new MessageResponse(false, "Attention", "Email existe déjà");
        }
        String encodedPassword= passwordEncoder.encode(candidat.getPassword());
        candidat.setPassword(encodedPassword);
        candidat=candidatRepository.save(candidat);
        eventPublisher.publishEvent(new OnRegistrationCompleteEvent(candidat));
        return new MessageResponse(true, "Succès", "Opération effectuée");
    }


    @Override
    public MessageResponse update(Candidat candidat) {
      boolean  exist = candidatRepository.existsByEmailAndId(candidat.getEmail(), candidat.getId());
        if (!exist) {
            exist = candidatRepository.existsByEmail(candidat.getEmail());
            if (exist) {
                return new MessageResponse(false, "Attention", "Email existe déjà");
            }
        }


        candidatRepository.save(candidat);

        return new MessageResponse(true, "Succès", "Opération effectuée");
    }

    @Override
    public MessageResponse delete(Integer id) {
        boolean exist = candidatRepository.existsByIdAndParticipationsIsNotNull(id);
        if (exist) {
            return new MessageResponse(false, "Attention",
                    "Candidat affecté a un ou plusieurs formations");
        }


        candidatRepository.deleteById(id);

        return new MessageResponse(true, "Succès", "Opération effectuée");
    }

    @Override
    public MessageResponse changeState(Candidat candidat) {
        candidatRepository.save(candidat);

        return new MessageResponse(true, "Succès", "Opération effectuée");
    }

    @Override
    public List<Candidat> findAll() {
        return candidatRepository.findAll();
    }

    @Override
    public Candidat findById(Integer id) {
        return candidatRepository.findById(id).orElse(null);
    }

    @Override
    public Candidat findByEmail(String email)
    {
        return candidatRepository.findByEmail(email);
    }


}
