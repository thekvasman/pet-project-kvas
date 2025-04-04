package com.example.pp.services;

import com.example.pp.mapping.ClientMapping;
import com.example.pp.model.dto.ClientDTO;
import com.example.pp.model.entity.ClientInfo;
import com.example.pp.repository.ClientRepository;
import com.example.pp.feign.FeignService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ClientService{

    private final FeignService feignService;
    private final ClientRepository clientRepository;
    private final ClientMapping clientMapping;


    public List<ClientInfo> getFilteredClients() {
        return feignService.getClients().stream()
                .filter(client -> client.getPhone().endsWith("7"))
                .filter(client -> client.getBirthday().getMonth().equals(LocalDate.now().getMonth()))
                .toList();
    }

    public ClientDTO getClientById(String clientId) {
        if(clientRepository.existsById(clientId)) {
            return clientMapping.toDto(clientRepository.getById(clientId));
        } else {
            return clientMapping.toDto(feignService.getClientById(clientId));
        }
    }

    public void saveClients() {
            clientRepository.saveAll(getFilteredClients());
    }
}
