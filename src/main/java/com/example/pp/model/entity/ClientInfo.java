package com.example.pp.model.entity;


import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Data
@Table
@AllArgsConstructor
@NoArgsConstructor
public class ClientInfo {

    @Id
    @Column(name = "clientId", columnDefinition = "VARCHAR(255)")
    private String clientId;

    private String name;
    private String middleName;
    private String phone;
    private LocalDate birthday;
    private Boolean messageSend;
}