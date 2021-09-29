package com.auth.authuser.service;

import com.auth.authuser.model.*;
import com.auth.authuser.repository.EmailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class SendEmailService {

    @Autowired
    private JavaMailSender javaMailSender;

    @Autowired
    private EmailRepository emailRepository;

    public void sendEmail(String to,String body, String subject){
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setFrom("appsimmdata@gmail.com");
        simpleMailMessage.setTo(to);
        simpleMailMessage.setSubject(subject);
        simpleMailMessage.setText(body);
        javaMailSender.send(simpleMailMessage);
    }

    public EmailHistory addEmail(Date dateSending, String typeDoc, Company company, Accountant accountant, Doc doc, ClientDoc clientDoc){
        EmailHistory emailHistory = new EmailHistory(dateSending, typeDoc,company,accountant,doc,clientDoc);
        return emailRepository.save(emailHistory);
    }

}
