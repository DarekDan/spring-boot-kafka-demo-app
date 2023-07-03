package com.example.demoapp.services;

import com.example.demoapp.models.Mail;
import com.example.demoapp.models.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class MailingService {

    private final KafkaTemplate<String, Mail> kafkaProducer;

    @Value("${kafka.topic.mailing_topic}")
    private String mailingTopic;

    public MailingService(KafkaTemplate<String, Mail> kafkaProducer) {
        this.kafkaProducer = kafkaProducer;
    }

    public Result<Boolean> sendMail(Mail mail) {
        var future = kafkaProducer.send(mailingTopic, mail);
        future.whenComplete((sendResult, exception) -> {
            if (exception != null) {
                log.warn("KAFKA-FAIL", exception);
                future.completeExceptionally(exception);
            } else {
                log.info("{}", sendResult);
                future.complete(sendResult);
            }
        });
        return future.isCompletedExceptionally() ? Result.error(future.exceptionNow()) : Result.success(true);
    }
}
