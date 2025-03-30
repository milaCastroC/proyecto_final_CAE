package com.gestVet.app.persistence.mapper;

import com.gestVet.app.domain.dto.PermisoDTO;
import com.gestVet.app.persistence.entity.Permiso;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.InheritInverseConfiguration;

@Mapper(componentModel = "spring")
public interface PermisoMapper {

    // Mapeo de Permiso a PermisoDTO
    @Mapping(source = "permisoId", target = "permisoId")
    @Mapping(source = "nombre", target = "nombre")
    @Mapping(target = "privilegioPermisos", ignore = true)
    PermisoDTO toDto(Permiso permiso);

    // Mapeo inverso de PermisoDTO a Permiso
    @InheritInverseConfiguration
    @Mapping(target = "privilegioPermisos", ignore = true)
    Permiso toEntity(PermisoDTO permisoDTO);
}