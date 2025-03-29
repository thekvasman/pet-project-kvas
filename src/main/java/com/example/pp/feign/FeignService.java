package com.example.pp.feign;

import com.example.pp.model.ClientInfo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@FeignClient(name = "clients-service", url = "${application.clients-service.url}")
public interface FeignService {

    @PostMapping("/api/v1/getClient")
    List<ClientInfo> getClients();

    @PostMapping("/api/v1/getClient/{clientId}")
    ClientInfo getClientById(@PathVariable("clientId") String clientId);
}
