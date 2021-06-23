package com.ant.examen.listners;

import com.ant.examen.entities.Invitation;
import com.ant.examen.services.InvitationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationListener;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;

@Component
public class InvitationListener implements ApplicationListener<OnInvitationCompleteEvent> {
    @Autowired
    private InvitationService invitationService;

    @Autowired
    private JavaMailSender mailSender;
    @Value("${app-url}")
    private String appUrl;

    @Override
    public void onApplicationEvent(OnInvitationCompleteEvent event) {
        Invitation invitation = event.getInvitation();


        String recipientAddress = invitation.getParticipation().getCandidat().getEmail();
        String subject;
        String object = "Bonjour," + invitation.getParticipation().getCandidat().getNom() + " " + invitation.getParticipation().getCandidat().getPrenom() + "\n";
        if (invitation.isEtat()) {
            subject = "Candidature acceptée";
            object += "Nous sommes heureux de vous annoncer que votre candidature  au sein de notre entreprise a été retenue.\n";
            SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm");
            String date = formatter.format(invitation.getDateInvitation());
            object += "Par conséquent, " + invitation.getParticipation().getExamen().getEntreprise().getRaisonSociale()
                    + " Vous a invité à rejoinder une visioconférence à" + date + ".\n"
                    + "Vous pouvez également ouvrir ce Meet à partir de notre plateforme.\n"+
                    "Cordialement,";
        } else {
            subject = "Candidature rejetée";
            object += "Nous avons bien reçu votre candidature  et nous vous en remercions.\n" +
                    "Cependant, malgré l’intérêt que suscite votre candidature, nous sommes au regret de ne pas pouvoir répondre favorablement à votre demande.\n" +
                    "Nous nous permettons, sauf avis contraire de votre part, de conserver votre curriculum vitae, afin de pouvoir vous contacter si un poste correspondant à votre profil venait à se présenter.\n" +
                    "Cordialement,";
        }

        SimpleMailMessage email = new SimpleMailMessage();
        email.setTo(recipientAddress);
        email.setSubject(subject);
        email.setText(object);
        mailSender.send(email);
    }
}

