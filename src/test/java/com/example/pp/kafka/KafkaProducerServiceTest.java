package com.example.pp.kafka;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;

@Service
@ExtendWith(MockitoExtension.class)
public class KafkaProducerServiceTest {

    @Mock
    private KafkaTemplate<String, String> kafkaTemplate;

    @InjectMocks
    private KafkaProducerService kafkaProducerService;

    @Test
    void sendMessageTest() {
        //GIVEN
        String topic = "test-topic";
        String phone = "huy777";
        String message = "test";
        //WHEN
        kafkaProducerService.sendMessage(topic, phone, message);
        //THEN
        verify(kafkaTemplate).send(eq(topic),eq(phone), eq(message));
    }
}
