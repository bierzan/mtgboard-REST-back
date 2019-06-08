package com.brzn.mtgboard.message;

import com.brzn.mtgboard.message.dto.MessageDataForEmail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

@Component
public class EmailSender {

    public JavaMailSender emailSender;

    @Autowired
    public EmailSender(JavaMailSender emailSender) {
        this.emailSender = emailSender;
    }

    public void sendSimpleMessage(String to, String subject, String text) {
        try {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setTo(to);
            message.setSubject(subject);
            message.setText(text);

            emailSender.send(message);
        } catch (MailException exception) {
            exception.printStackTrace();
        }
    }

    public void sendTemplateMessage(MessageDataForEmail msg) {
        String template = "Wiadomość od: %s, %s\n" +
                "do: %s, %s\n" +
                "Dotyczy oferty:\n" +
                "%s\n\n" +
                "Treść wiadomości:\n" +
                "%s\n" +
                "\n\n" +
                "Wiadomość wygenerowana automatycznie przez serwis MTGBOARD";
        String text = String.format(template,
                msg.getUserFrom(), msg.getMailFrom(),
                msg.getUserTo(), msg.getMailTo(),
                msg.getOffer(),
                msg.getMessage());
        sendSimpleMessage(msg.getMailTo(), msg.getSubject(), text);
    }

}