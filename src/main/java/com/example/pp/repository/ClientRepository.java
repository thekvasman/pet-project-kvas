package com.example.pp.repository;

import com.example.pp.model.ClientInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ClientRepository extends JpaRepository<ClientInfo, Long> {
//    List<ClientInfo> findClients(String ending, int month);

    List<ClientInfo> findByMessageSendFalse();
}
