package com.gestVet.app.persistence.mapper;

import com.gestVet.app.domain.dto.PrivilegioDTO;
import com.gestVet.app.persistence.entity.Privilegio;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.InheritInverseConfiguration;

@Mapper(componentModel = "spring")
public interface PrivilegioMapper {

    // Mapeo de Privilegio a PrivilegioDTO
    @Mapping(source = "privilegioId", target = "privilegioId")
    @Mapping(source = "nombre", target = "nombre")
    PrivilegioDTO toDto(Privilegio privilegio);

    // Mapeo inverso de PrivilegioDTO a Privilegio
    @InheritInverseConfiguration
    @Mapping(target = "administradores", ignore = true)
    @Mapping(target = "privilegioPermisos", ignore = true)
    Privilegio toEntity(PrivilegioDTO privilegioDTO);
}