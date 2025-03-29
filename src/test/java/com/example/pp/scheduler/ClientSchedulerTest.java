package com.example.pp.scheduler;

import com.example.pp.kafka.KafkaProducerService;
import com.example.pp.model.ClientInfo;
import com.example.pp.repository.ClientRepository;
import com.example.pp.services.ClientService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
@ActiveProfiles("test")
public class ClientSchedulerTest {

    @Autowired
    private ClientScheduler clientScheduler;

    @Autowired
    private ClientRepository clientRepository;

    @MockBean
    private KafkaProducerService kafkaProducerService;

    @MockBean
    private ClientService clientService;

    @AfterEach
    void tearDown() {
        clientRepository.deleteAll();
    }

    @Test
    void scheduledTaskTest(){
        //GIVEN
        List<ClientInfo> mock = List.of(
                createTestClient("1a", "+79876547777"),
                createTestClient("2b", "+79128765477")
        );

        clientRepository.saveAll(mock);

        //WHEN
        clientScheduler.scheduledTask();

        clientRepository.flush();

        //THEN
        List<ClientInfo> savedClients = clientRepository.findAll();
        assertThat(savedClients).hasSize(2);

        verify(kafkaProducerService, times(2)).sendMessage(
                eq("messageSMS"),
                anyString(),
                contains("10%")
        );

        assertThat(savedClients)
                .extracting(ClientInfo::getMessageSend)
                .containsOnly(true);

        assertThat(savedClients).allMatch(ClientInfo::getMessageSend);
    }

    private ClientInfo createTestClient(String clientId, String phone) {
        ClientInfo client = new ClientInfo();
        client.setClientId(clientId);
        client.setPhone(phone);
        client.setName("Test");
        client.setMiddleName("User");
        client.setMessageSend(false);
        return client;
    }

}