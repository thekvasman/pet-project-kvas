package com.example.pp.services;

import com.example.pp.feign.FeignService;
import com.example.pp.model.ClientInfo;
import com.example.pp.repository.ClientRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class ClientServiceTest {

    @MockBean
    private FeignService feignService;

    @MockBean
    private ClientRepository clientRepository;

    @Autowired
    private ClientService clientService;

    private List<ClientInfo> mock;

    @BeforeEach
    void setUp() {
        mock = List.of(
                new ClientInfo("1a", "Huy", "Huyevich", "79876546427", LocalDate.of(1997, 4, 20), true),
                new ClientInfo("2b", "Nikita", "Borisovich", "79877777778", LocalDate.now(), false),
                new ClientInfo("3c", "Blyadota", "Ivanovna", "77777777777", LocalDate.now(), false)
        );
    }

    @Test
    void getFilteredClientsTest() {
        //given
        List<ClientInfo> expectedResult = List.of(
                new ClientInfo("3c", "Blyadota", "Ivanovna", "77777777777", LocalDate.now(), false)
        );
        List<ClientInfo> actualResult;

        //mock set
        Mockito.when(feignService.getClients()).thenReturn(mock);
        System.out.println("feign mock: " + feignService.getClients());

        //when
        actualResult = clientService.getFilteredClients();

        //then
        assertThat(actualResult).usingRecursiveComparison().isEqualTo(expectedResult);
    }

    @Test
    void getClientByIdTest() {

        //given
        String clientId = "2b";
        ClientInfo expectedResult = mock.get(1);

        //mock set
        Mockito.when(feignService.getClientById(clientId)).thenReturn(mock.get(1));

        //when
        ClientInfo actualResult = clientService.getClientById(clientId);

        //then
        assertThat(actualResult).usingRecursiveComparison().isEqualTo(expectedResult);
    }

    @Test
    void saveClientTest() {
        //GIVEN
        List<ClientInfo> expectedResult = mock;
        //WHEN
        clientService.saveClients(mock);
        //THEN
        Mockito.verify(clientRepository).saveAll(expectedResult);
    }
}
