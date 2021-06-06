package com.ant.examen.services.imp;

import com.ant.examen.dto.MessageResponse;
import com.ant.examen.entities.ReponseCandidat;
import com.ant.examen.repository.ReponseCandidatRepository;
import com.ant.examen.services.ReponseCandidatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Service
public class ReponseCandidatServiceImp implements ReponseCandidatService {
    @Autowired
    private ReponseCandidatRepository reponseCandidatRepository;
    @Transactional
    @Override
    public MessageResponse save(List<ReponseCandidat> reponseCandidats) {
        reponseCandidatRepository.saveAll(reponseCandidats);
        return new MessageResponse(true, "Succès", "Opération effectuée");
    }
}
