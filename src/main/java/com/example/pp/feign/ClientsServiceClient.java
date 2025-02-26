package com.example.pp.feign;

import com.example.pp.model.ClientInfo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "clients-service", url = "http://Clients")
public interface ClientsServiceClient {

    @GetMapping("/api/v1/clients")
    List<ClientInfo> getClients();

    @GetMapping("/api/v1/clients/{clientId}")
    ClientInfo getClientById(@PathVariable Long clientId);
}
