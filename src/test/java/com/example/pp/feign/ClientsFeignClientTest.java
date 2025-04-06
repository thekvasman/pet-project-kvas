package com.example.pp.feign;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class ClientsFeignClientTest {

    @Autowired
    private ClientsFeignClient clientsFeignClient;

    @Test
    void getClientsTest() {
        assertFalse(clientsFeignClient.getClients().isEmpty());
    }

    @Test
    void getClientByIdTest() {
        String clientId = "123-QEW";
        assertNotNull(clientsFeignClient.getClientById(clientId));
    }
}
