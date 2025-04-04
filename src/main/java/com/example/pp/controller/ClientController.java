package com.example.pp.controller;

import com.example.pp.model.dto.ClientDTO;
import com.example.pp.services.ClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/getClient")
public class ClientController {
    private final ClientService clientService;

    @GetMapping("/{clientId}")
    public ClientDTO getClientById(@PathVariable String clientId) {
        return clientService.getClientById(clientId);
    }
}
