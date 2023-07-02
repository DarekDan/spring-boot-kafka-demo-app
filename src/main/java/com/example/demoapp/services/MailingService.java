package com.example.demoapp.services;

import com.example.demoapp.models.Mail;
import com.example.demoapp.models.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class MailingService {

    @Autowired
    private KafkaTemplate<String, Mail> kafkaProducer;

    @Value("${kafka.topic.mailing_topic}")
    private String mailingTopic;

    public Result<Boolean> sendMail(Mail mail) {
        var future = kafkaProducer.send(mailingTopic, mail);
        future.whenComplete((sendResult, exception) -> {
            log.info("{}", sendResult);
            if (exception != null) {
                future.completeExceptionally(exception);
            } else {
                future.complete(sendResult);
            }
        });
        return future.isCompletedExceptionally() ? Result.error(future.exceptionNow()) : Result.success(true);
    }
}
