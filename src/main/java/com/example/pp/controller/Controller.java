package com.example.pp.controller;

import com.example.pp.model.ClientInfo;
import com.example.pp.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/clients")
public class Controller {

    @Autowired
    private ClientService clientService;

    @GetMapping
    public List<ClientInfo> getClients() {
        return clientService.getClients();
    }

    @GetMapping("/{clientId}")
    public ClientInfo getClient(@PathVariable Long clientId) {
        return clientService.getClientById(clientId);
    }
}
