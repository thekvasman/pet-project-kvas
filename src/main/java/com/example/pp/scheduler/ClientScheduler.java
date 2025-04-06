package com.example.pp.scheduler;

import com.example.pp.kafka.ClientKafkaProducer;
import com.example.pp.model.entity.ClientInfo;
import com.example.pp.repository.ClientRepository;
import com.example.pp.services.serviceImpl.ClientServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ClientScheduler {

    private final ClientServiceImpl clientServiceImpl;
    private final ClientKafkaProducer clientKafkaProducer;
    private final ClientRepository clientRepository;

    @Value("${application.discount}")
    private String discount;


    @Scheduled(cron = "0 0 * * * *")
    public void scheduledTask() {
        clientServiceImpl.saveClients();
        List<ClientInfo> clients = clientRepository.findByMessageSendFalse();
        for(ClientInfo client : clients) {
            String message = client.getName() + " " + client.getMiddleName() + ", для Вас в этом месяце действует скидка " + discount;
            if(Calendar.HOUR_OF_DAY < 19) {
                clientKafkaProducer.sendMessage("messageSMS", client.getPhone(), message);
                client.setMessageSend(true);
                clientRepository.save(client);
            }
        }
    }
}
