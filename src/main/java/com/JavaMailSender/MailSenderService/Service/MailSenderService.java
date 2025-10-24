package com.JavaMailSender.MailSenderService.Service;

import com.JavaMailSender.MailSenderService.Entity.ContactRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class MailSenderService {

    @Autowired
    private JavaMailSender javaMailSender;

    public void sendContactMail(ContactRequest req, String address) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(address);
        message.setSubject("Portfolio Contact: " + req.getSubject());
        String text = ""
                + "Name: " + req.getName() + "\n"
                + "Email: " + req.getEmail() + "\n\n"
                + "Message:\n" + req.getMessage();
        message.setText(text);
        javaMailSender.send(message);
    }
}
