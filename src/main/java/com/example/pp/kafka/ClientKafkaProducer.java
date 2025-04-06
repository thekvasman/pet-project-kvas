package com.example.pp.kafka;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class ClientKafkaProducer {

    private final KafkaTemplate<String, String> kafkaTemplate;

    public ClientKafkaProducer(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendMessage(String topic, String phone, String message) {
        kafkaTemplate.send(topic, phone, message);
    }
}
