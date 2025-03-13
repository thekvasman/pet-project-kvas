package com.example.pp.service;

import com.example.pp.model.ClientInfo;
import com.example.pp.repository.ClientRepository;
import com.example.pp.feign.FeignService;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ClientService{

    private final FeignService feignService;
    private final ClientRepository clientRepository;

    @Scheduled(cron = "0 * * * * *")
    public void scheduledClients() {
        clientRepository.saveAll(getFilteredClients());
    }

    public List<ClientInfo> getFilteredClients() {
        List<ClientInfo> clients = feignService.getClients();
        return clients.stream()
                .filter(client -> client.getPhone().endsWith("7"))
                .filter(client -> client.getBirthday().getMonth().equals(LocalDate.now().getMonth()))
                .toList();
    }

    public ClientInfo getClientById(String clientId) {
        return feignService.getClientById(clientId);
    }
}
