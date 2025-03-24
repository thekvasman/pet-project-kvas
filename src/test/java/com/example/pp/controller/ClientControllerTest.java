package com.example.pp.controller;

import com.example.pp.model.ClientInfo;
import com.example.pp.scheduler.SchedulerService;
import com.example.pp.services.ClientService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
public class ClientControllerTest {

    @Mock
    private ClientService clientService;

    @Mock
    private SchedulerService schedulerService;

    @InjectMocks
    private ClientController clientController;

    private MockMvc mockMvc;

    @Test
    void getClientByIdTest() throws Exception {

        //given
        String clientId = "1a";
        ClientInfo expectedClient = new ClientInfo();

        //mock set
        when(clientService.getClientById(clientId)).thenReturn(expectedClient);
        mockMvc = MockMvcBuilders.standaloneSetup(clientController).build();

        //when
        mockMvc.perform(get("/api/v1/getClient/{clientId}", clientId))
                .andExpect(status().isOk());

        //then
        verify(clientService, times(1)).getClientById(clientId);
    }

    @Test
    void getClientsTest() throws Exception {
        //given
        doNothing().when(schedulerService).scheduledTask();
        mockMvc = MockMvcBuilders.standaloneSetup(clientController).build();

        //when
        mockMvc.perform(get("/api/v1/getClient"))
                .andExpect(status().isOk());

        //then
        verify(schedulerService, times(1)).scheduledTask();
    }

    @Test
    void getClientNotFoundTest() throws Exception {
        //given
        String clientId = "9z";
        when(clientService.getClientById(clientId)).thenReturn(null);
        mockMvc = MockMvcBuilders.standaloneSetup(clientController).build();

        //when
        mockMvc.perform(get("/api/v1/getClient/{clientId}", clientId))
                .andExpect(status().isOk());

        //then
        verify(clientService, times(1)).getClientById(clientId);
    }
}
