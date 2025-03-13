package com.example.pp.service;

import com.example.pp.model.ClientInfo;
import com.example.pp.repository.ClientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SmsService {
    private final KafkaTemplate<String, String> kafkaTemplate;
    private final ClientRepository clientRepository;

    @Value("${discount}")
    private String discount;

    @Scheduled(cron = "0 * * * * *")
    public void sendMessages() {
        if (Calendar.getInstance().get(Calendar.HOUR_OF_DAY) < 19) {
            List<ClientInfo> clients = clientRepository.findByMessageSendFalse();
            clients.forEach(client -> {
                String message = String.format("%s, в этом месяце для вас действует скидка %s", client.getName(), discount);
                kafkaTemplate.send("sms", client.getPhone(), message);
                client.setMessageSend(true);
                clientRepository.save(client);
            });
        }
    }
}
