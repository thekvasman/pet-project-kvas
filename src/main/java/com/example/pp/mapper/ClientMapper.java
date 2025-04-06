package com.example.pp.mapper;

import com.example.pp.model.dto.ClientDTO;
import com.example.pp.model.entity.ClientInfo;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ClientMapper {
    ClientDTO toDto(ClientInfo clientInfo);
}
