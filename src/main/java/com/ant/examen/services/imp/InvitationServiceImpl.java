package com.ant.examen.services.imp;

import com.ant.examen.entities.Invitation;
import com.ant.examen.listners.OnInvitationCompleteEvent;
import com.ant.examen.repository.InvitationRepository;
import com.ant.examen.responses.MessageResponse;
import com.ant.examen.services.InvitationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class InvitationServiceImpl implements InvitationService {
    @Autowired
    private InvitationRepository invitationRepository;
    @Autowired
    private ApplicationEventPublisher eventPublisher;

    @Override
    public MessageResponse save(Invitation invitation) {
        if (invitation.isEtat()) {
            String roomName = UUID.randomUUID().toString();
            invitation.setRoomName(roomName);
        }
        invitationRepository.save(invitation);
        eventPublisher.publishEvent(new OnInvitationCompleteEvent(invitation));
        return new MessageResponse(true, "Succès", "Opération effectuée");
    }

    @Override
    public List<Invitation> findByEntreprise(Integer id) {
        return invitationRepository.findByEntreprise(id);
    }

    @Override
    public List<Invitation> findByCandidat(Integer id) {
        return invitationRepository.findByCandidat(id);
    }
}
