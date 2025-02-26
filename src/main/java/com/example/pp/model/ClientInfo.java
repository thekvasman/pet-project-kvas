package com.example.pp.model;


import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
@Builder
@Getter
@Setter
@Table(name = "Clients")
@AllArgsConstructor
public class ClientInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long clientId;

    private String name;
    private String phone;
    private Date birthday;
    private boolean messageSend;

    public ClientInfo() {

    }
    //clientId - varchar 255
    //name - varchar 255
    //middleName - varchar 255
    //surname - varchar 255
    //age - Long
    //birthday - Date
    //phone - varchar 255
}