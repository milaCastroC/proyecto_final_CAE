package com.gestVet.app.persistence.mapper;

import com.gestVet.app.domain.dto.TipoCitaDTO;
import com.gestVet.app.persistence.entity.TipoCita;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.InheritInverseConfiguration;

@Mapper(componentModel = "spring")
public interface TipoCitaMapper {

    // Mapeo de TipoCita a TipoCitaDTO
    @Mapping(source = "tipoCitaId", target = "tipoCitaId")
    @Mapping(source = "nombre", target = "nombre")
    TipoCitaDTO toDto(TipoCita tipoCita);

    // Mapeo inverso de TipoCitaDTO a TipoCita
    @InheritInverseConfiguration
    @Mapping(target = "citas", ignore = true)
    TipoCita toEntity(TipoCitaDTO tipoCitaDTO);
}