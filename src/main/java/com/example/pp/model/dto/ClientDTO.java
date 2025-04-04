package com.example.pp.model.dto;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;

@Data
@RequiredArgsConstructor
public class ClientDTO {

    private String id;
    private String name;
    private String middleName;
    private String phone;
    private LocalDate birthday;
    private Boolean messageSend;
}
