package com.example.demoapp.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class KafkaTopicConfig {

    @Value("${kafka.topic.mailing_topic}")
    private String mailingTopic;


    @Bean
    NewTopic taskTopic() {
        return TopicBuilder.name(mailingTopic)
            .partitions(1)
            .replicas(1)
            .build();
    }
}

