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

    @Value("${app.receive-email:${spring.mail.username:}}")
    private String receiveEmail;

    @Autowired
    private MailSenderService mailSenderService;

    @PostMapping("/send")
    public ResponseEntity<?> sendEmail(@Valid @RequestBody ContactRequest req) {
        // pick the address to send to
        String to = (receiveEmail != null && !receiveEmail.isBlank()) ? receiveEmail : System.getenv("RECEIVE_EMAIL");
        if (to == null || to.isBlank()) {
            return ResponseEntity.status(500).body("Receiver email is not configured (set app.receive-email or RECEIVE_EMAIL env var)");
        }

        mailSenderService.sendCotactMail(req,to);
        return ResponseEntity.ok().body("message sent");
    }
}
