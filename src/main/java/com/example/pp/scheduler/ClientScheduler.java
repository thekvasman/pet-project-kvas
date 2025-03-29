package com.example.pp.scheduler;

import com.example.pp.kafka.KafkaProducerService;
import com.example.pp.model.ClientInfo;
import com.example.pp.repository.ClientRepository;
import com.example.pp.services.ClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ClientScheduler {

    private final ClientService clientService;
    private final KafkaProducerService kafkaProducerService;
    private final ClientRepository clientRepository;

    @Value("${application.discount}")
    private String discount;


    @Scheduled(cron = "0 0 * * * *")
    public void scheduledTask() {
        clientService.saveClients(clientService.getFilteredClients());
        List<ClientInfo> clients = clientRepository.findByMessageSendFalse();
        for(ClientInfo client : clients) {
            String message = client.getName() + " " + client.getMiddleName() + ", для Вас в этом месяце действует скидка " + discount;
            if(Calendar.HOUR_OF_DAY < 19) {
                kafkaProducerService.sendMessage("messageSMS", client.getPhone(), message);
                client.setMessageSend(true);
                clientRepository.save(client);
            }
        }
    }
}
