package com.example.pp.scheduler;

import com.example.pp.kafka.KafkaProducerService;
import com.example.pp.model.ClientInfo;
import com.example.pp.repository.ClientRepository;
import com.example.pp.services.ClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SchedulerService {

    private final ClientService clientService;
    private final KafkaProducerService kafkaProducerService;
    private final ClientRepository clientRepository;

    @Value("${discount}")
    private String discount;


    @Scheduled(cron = "0 * * * * *")
    public void scheduledTask() {
        List<ClientInfo> clients = clientService.getFilteredClients();
        clientService.saveClients(clients);
        clients = clientRepository.findByMessageSendFalse();
        for(ClientInfo client : clients) {
            String message = client.getName() + " " + client.getMiddleName() + ", для Вас в этом месяце действует скидка " + discount;
            kafkaProducerService.sendMessage("messageSMS", client.getPhone(), message);
            client.setMessageSend(true);
            clientRepository.save(client);
        }
    }
}
