package com.example.pp.controller;

import com.example.pp.mapper.ClientMapper;
import com.example.pp.model.dto.ClientDTO;
import com.example.pp.services.serviceImpl.ClientServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/getClient")
public class ClientController {
    private final ClientServiceImpl clientServiceImpl;
    private final ClientMapper clientMapper;

    @GetMapping("/{clientId}")
    public ClientDTO getClientById(@PathVariable String clientId) {
        return clientMapper.toDto(clientServiceImpl.getClientById(clientId));
    }
}
