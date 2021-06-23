package com.ant.examen.listners;

import com.ant.examen.entities.Invitation;
import com.ant.examen.entities.User;
import lombok.Getter;
import lombok.Setter;
import org.springframework.context.ApplicationEvent;


@Getter
@Setter
public class OnInvitationCompleteEvent extends ApplicationEvent {

    private Invitation invitation;

    public OnInvitationCompleteEvent(Invitation invitation) {
        super(invitation);

        this.invitation = invitation;

    }
}
