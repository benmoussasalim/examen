package com.ant.examen.listners;
import com.ant.examen.entities.User;

import lombok.*;
import org.springframework.context.ApplicationEvent;
import java.util.Locale;


@Getter
@Setter
public class OnRegistrationCompleteEvent extends ApplicationEvent {

    private User user;

    public OnRegistrationCompleteEvent(User user) {
        super(user);

        this.user = user;

    }
}
