package com.altimetrix.ecommerce.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class NotificationService {

    @Autowired
    private JavaMailSender emailSender;

    public void sendEmail(String emailId, String subject,String text) {
    	
        SimpleMailMessage message = new SimpleMailMessage(); 
        message.setFrom("noreply@emcommerce.com");
        message.setTo(emailId); 
        message.setSubject(subject); 
        message.setText(text);
        emailSender.send(message);
      
    }
}