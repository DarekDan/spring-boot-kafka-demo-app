package com.example.demoapp.components;

import com.example.demoapp.models.Mail;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class KafkaTopicListener {
    @KafkaListener(topics = {"packaging_mailing_topic"}, groupId = "mail-group")
    public void handleSendingEmail(Mail mail) {
        log.info("Preparing to send shipping info to this email [{}]", mail.getEmail());
        log.info("Shipping Information");
        log.info("{}", mail);
    }
}
