package com.example.pp.services;

import com.example.pp.model.entity.ClientInfo;

import java.util.List;

public interface ClientService {

    List<ClientInfo> getFilteredClients();

    ClientInfo getClientById(String clientId);

    void saveClients();
}
