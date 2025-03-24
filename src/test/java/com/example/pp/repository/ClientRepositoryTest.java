package com.example.pp.repository;

import com.example.pp.model.ClientInfo;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;


@DataJpaTest
@ActiveProfiles("test")
public class ClientRepositoryTest {

    @Autowired
    private ClientRepository repo;
    @Autowired
    private ClientRepository clientRepository;

    @Test
    void findByMessageSendFalseTest() {
        //GIVEN
        ClientInfo clientWithSms = new ClientInfo();
        clientWithSms.setMessageSend(true);
        clientRepository.save(clientWithSms);

        ClientInfo clientWithoutSms = new ClientInfo();
        clientWithoutSms.setMessageSend(false);
        clientRepository.save(clientWithoutSms);
        //WHEN
        List<ClientInfo> result = clientRepository.findByMessageSendFalse();
        //THEN
        Assertions.assertThat(result)
                .hasSize(1)
                .allMatch(client -> !client.isMessageSend());
    }
}
