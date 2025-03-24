package com.example.pp.feign;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class FeignServiceTest {

    @Autowired
    private FeignService feignService;

    @Test
    void getClientsTest() {
        assertFalse(feignService.getClients().isEmpty());
    }

    @Test
    void getClientByIdTest() {
        String clientId = "123-QEW";
        assertNotNull(feignService.getClientById(clientId));
    }
}
