package com.example.pp.mapping;

import com.example.pp.model.dto.ClientDTO;
import com.example.pp.model.entity.ClientInfo;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ClientMapping {
    ClientDTO toDto(ClientInfo clientInfo);
}
