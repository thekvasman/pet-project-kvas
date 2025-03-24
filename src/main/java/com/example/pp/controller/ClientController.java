package com.example.pp.controller;

import com.example.pp.model.ClientInfo;
import com.example.pp.services.ClientService;
import com.example.pp.scheduler.SchedulerService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/getClient")
public class ClientController {
    private final ClientService clientService;
    private final SchedulerService schedulerService;

    @GetMapping
    public void getClients() {
        schedulerService.scheduledTask();
    }

    @GetMapping("/{clientId}")
    public ClientInfo getClientById(@PathVariable String clientId) {
        return clientService.getClientById(clientId);
    }
}
