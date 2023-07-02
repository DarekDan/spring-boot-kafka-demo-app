package com.example.demoapp.controllers;

import com.example.demoapp.models.Mail;
import com.example.demoapp.services.MailingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1")
public class MailingController {

    @Autowired
    private MailingService mailingService;

    @PostMapping("/mailing")
    public ResponseEntity<String> doMailing(@RequestBody Mail mail) {
        mailingService.sendMail(mail);
        return new ResponseEntity<String>(HttpStatus.OK);
    }
}
