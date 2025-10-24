package com.JavaMailSender.MailSenderService.Controller;

import com.JavaMailSender.MailSenderService.Entity.ContactRequest;
import com.JavaMailSender.MailSenderService.Service.MailSenderService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/email")
@CrossOrigin(origins = "*")
public class MailSenderController {

    @Value("${app.receive-email}")
    private String receiveEmail;

    @Autowired
    private MailSenderService mailSenderService;

    @PostMapping("/send")
    public ResponseEntity<?> sendEmail(@Valid @RequestBody ContactRequest req) {
        try {
            mailSenderService.sendContactMail(req, receiveEmail);
            return ResponseEntity.ok("Message sent successfully!");
        } catch (Exception e) {
            e.printStackTrace(); // For debugging in Railway logs
            return ResponseEntity.internalServerError()
                    .body("Failed to send email: " + e.getMessage());
        }
    }
}
