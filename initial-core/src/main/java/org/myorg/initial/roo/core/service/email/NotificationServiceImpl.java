package org.myorg.initial.roo.core.service.email;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;

public class NotificationServiceImpl {

    @Autowired
    private transient MailSender mailTemplate;

    @Autowired
    private transient SimpleMailMessage templateMessage;

    public void sendMessage(String mailTo, String message) {
        org.springframework.mail.SimpleMailMessage mailMessage = new org.springframework.mail.SimpleMailMessage(templateMessage);
        mailMessage.setTo(mailTo);
        mailMessage.setText(message);
        mailTemplate.send(mailMessage);
    }
}
