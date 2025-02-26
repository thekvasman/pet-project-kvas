package com.example.pp.service;

import com.example.pp.model.ClientInfo;
import com.example.pp.repository.ClientRepository;
import com.example.pp.feign.ClientsServiceClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.List;

@Service
public class ClientService{

    @Autowired
    private ClientsServiceClient clientsServiceClient;

    @Autowired
    private ClientRepository clientRepository;

    @Scheduled(cron = "0 0 * * * *")
    public List<ClientInfo> getClients() {
        List<ClientInfo> clients = clientsServiceClient.getClients();
        int currentMonth = Calendar.getInstance().get(Calendar.MONTH) + 1;

        return clients.stream()
                .filter(client -> client.getPhone().endsWith("7"))
                .filter(client -> client.getBirthday().getMonth() == currentMonth)
                .toList();
    }

    public ClientInfo getClientById(Long clientId) {
        ClientInfo client = clientsServiceClient.getClientById(clientId);
        int currentMonth = Calendar.getInstance().get(Calendar.MONTH) + 1;
        if(client != null && client.getPhone().endsWith("7") && client.getBirthday().getMonth() == currentMonth){
            return client;
        }
        return null;
    }
}
