package com.example.demoapp.controllers;

import com.example.demoapp.models.Mail;
import com.example.demoapp.models.Result;
import com.example.demoapp.services.MailingService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("api/v1")
public class MailingController {

    private final MailingService mailingService;

    public MailingController(MailingService mailingService) {
        this.mailingService = mailingService;
    }

    @PostMapping("/mailing")
    public Mono<Result<Boolean>> doMailing(@RequestBody Mail mail) {
        return Mono.fromCallable(() -> mailingService.sendMail(mail));
    }
}

