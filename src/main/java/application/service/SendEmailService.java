package application.service;

import application.domain.EmailMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.mail.MailException;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class SendEmailService {
    private JavaMailSender javaMailSender;
    private static Logger LOGGER = LoggerFactory.getLogger(SendEmailService.class);

    public SendEmailService(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

    public void sendMessage(EmailMessage emailMessage) {
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setTo(emailMessage.getMailTo());
        simpleMailMessage.setSubject(emailMessage.getSubject());
        simpleMailMessage.setText(emailMessage.getBody());
        try {
            javaMailSender.send(simpleMailMessage);
            LOGGER.info("Wiadomosc zostąła wysłana, do: " + emailMessage.getMailTo());
        } catch (MailException e) {
            LOGGER.error("Błąd wysyalania wiadomosci, do: " + emailMessage.getMailTo());
        }
    }
}
