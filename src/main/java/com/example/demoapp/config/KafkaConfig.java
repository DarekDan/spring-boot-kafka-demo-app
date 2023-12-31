package com.example.demoapp.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

import java.util.List;

@Configuration
public class KafkaConfig {

    @Value("${kafka.topic.mailing_topic}")
    private String mailingTopic;

    @Value("${kafka.topic.account_topic}")
    private String accoutTopic;


    @Bean(name = "mail")
    NewTopic mailingTopic() {
        return TopicBuilder.name(mailingTopic)
                        .partitions(1)
                        .replicas(1)
                        .build();
    }

    @Bean(name = "account")
    NewTopic accountTopic() {
        return TopicBuilder.name(accoutTopic)
                        .partitions(1)
                        .replicas(1)
                        .build();
    }
}

