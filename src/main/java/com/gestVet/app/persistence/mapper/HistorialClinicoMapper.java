package com.gestVet.app.persistence.mapper;

import com.gestVet.app.domain.dto.HistorialClinicoDTO;
import com.gestVet.app.persistence.entity.*;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.InheritInverseConfiguration;

@Mapper(componentModel = "spring")
public interface HistorialClinicoMapper {

    // Mapeo de HistorialClinico a HistorialClinicoDTO
    @Mapping(source = "historialClinicoId", target = "historialClinicoId")
    @Mapping(source = "mascota", target = "mascotaId")
    HistorialClinicoDTO toDto(HistorialClinico historialClinico);

    // Mapeo inverso de HistorialClinicoDTO a HistorialClinico
    @InheritInverseConfiguration
    @Mapping(target = "mascota", source = "mascotaId")
    @Mapping(target = "itemHistorials", ignore = true)
    HistorialClinico toEntity(HistorialClinicoDTO historialClinicoDTO);

    // Métodos de conversión para las relaciones
    default Long mapMascota(Mascota mascota) {
        return mascota != null ? mascota.getMascotaId() : null;
    }

    default Mascota mapMascota(Long mascotaId) {
        if (mascotaId != null) {
            Mascota mascota = new Mascota();
            mascota.setMascotaId(mascotaId);
            return mascota;
        }
        return null;
    }
}