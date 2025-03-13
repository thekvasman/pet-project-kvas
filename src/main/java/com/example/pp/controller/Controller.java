package com.example.pp.controller;

import com.example.pp.model.ClientInfo;
import com.example.pp.service.ClientService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/getClient")
public class Controller {
    private final ClientService clientService;

    @GetMapping
    public void getClients() {
        clientService.scheduledClients();
    }

    @GetMapping("/{clientId}")
    public ClientInfo getClient(@PathVariable String clientId) {
        return clientService.getClientById(clientId);
    }
}
