package com.example.pp.services.serviceImpl;

import com.example.pp.model.entity.ClientInfo;
import com.example.pp.repository.ClientRepository;
import com.example.pp.feign.ClientsFeignClient;
import com.example.pp.services.ClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ClientServiceImpl implements ClientService {

    private final ClientsFeignClient clientsFeignClient;
    private final ClientRepository clientRepository;


    public List<ClientInfo> getFilteredClients() {
        return clientsFeignClient.getClients().stream()
                .filter(client -> client.getPhone().endsWith("7"))
                .filter(client -> client.getBirthday().getMonth().equals(LocalDate.now().getMonth()))
                .toList();
    }

    public ClientInfo getClientById(String clientId) {
        if(clientRepository.existsById(clientId)) {
            return clientRepository.getById(clientId);
        } else {
            return clientsFeignClient.getClientById(clientId);
        }
    }

    public void saveClients() {
            clientRepository.saveAll(getFilteredClients());
    }
}
