package com.school.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class MailService {
    @Autowired
    private JavaMailSender javaMailSender;

    public void sendCredentialMail(Long userId ,String email) {

        String subject = "Your Login Credential!";
        String body = "Your UserId is "+userId+".\nDo Login with This userId.\nDo not share this userId with anyone.";

        String from = "tejasshah2k19@gmail.com";

        SimpleMailMessage mailMessage=new SimpleMailMessage();
        mailMessage.setSubject(subject);
        mailMessage.setText(body);
        mailMessage.setFrom(from);
        mailMessage.setTo(email);
        javaMailSender.send(mailMessage);


    }

}
