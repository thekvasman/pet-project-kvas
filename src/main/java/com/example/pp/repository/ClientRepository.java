package com.example.pp.repository;

import com.example.pp.model.ClientInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClientRepository extends JpaRepository<ClientInfo, String> {

    List<ClientInfo> findByMessageSendFalse();
}
