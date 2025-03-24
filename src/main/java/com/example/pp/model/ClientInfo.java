package com.example.pp.model;


import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Data
@Builder
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
    private boolean messageSend;

}