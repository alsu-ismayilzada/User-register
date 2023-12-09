package com.example.demo.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class MailSenderService {

    @Value("${spring.mail.username}")
    private String sender;

    private final JavaMailSender mailSender;

    public MailSenderService(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }


    public void sendMail(String recipient, String message, String password){
        SimpleMailMessage message1 = new SimpleMailMessage();

        message1.setFrom(sender);
        message1.setTo(recipient);
        message1.setSubject(password);
        message1.setText(message);

        mailSender.send(message1);
    }
}
