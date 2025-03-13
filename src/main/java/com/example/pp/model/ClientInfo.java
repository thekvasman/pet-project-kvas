package com.example.pp.model;


import lombok.*;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;

@Entity
@Data
@Builder
//@Accessors(chain = true)
@Table
@AllArgsConstructor
@NoArgsConstructor
public class ClientInfo {

    @Id
    private String clientId;

    private String name;
    private String phone;
    private LocalDate birthday;
    private boolean messageSend;

    //clientId - varchar 255
    //name - varchar 255
    //middleName - varchar 255
    //surname - varchar 255
    //age - Long
    //birthday - Date
    //phone - varchar 255
}