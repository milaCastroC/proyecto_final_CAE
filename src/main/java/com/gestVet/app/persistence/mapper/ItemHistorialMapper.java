package com.gestVet.app.persistence.mapper;

import com.gestVet.app.domain.dto.ItemHistorialDTO;
import com.gestVet.app.persistence.entity.*;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.InheritInverseConfiguration;

@Mapper(componentModel = "spring")
public interface ItemHistorialMapper {

    // Mapeo de ItemHistorial a ItemHistorialDTO
    @Mapping(source = "itemHistorialId", target = "itemHistorialId")
    @Mapping(source = "historialClinico", target = "historialClinicoId")
    @Mapping(source = "fecha", target = "fecha")
    @Mapping(source = "diagnostico", target = "diagnostico")
    @Mapping(source = "tratamiento", target = "tratamiento")
    @Mapping(source = "observaciones", target = "observaciones")
    @Mapping(source = "tipos", target = "tipos")
    ItemHistorialDTO toDto(ItemHistorial itemHistorial);

    // Mapeo inverso de ItemHistorialDTO a ItemHistorial
    @InheritInverseConfiguration
    @Mapping(target = "historialClinico", source = "historialClinicoId")
    ItemHistorial toEntity(ItemHistorialDTO itemHistorialDTO);

    // Métodos de conversión para las relaciones
    default Long mapHistorialClinico(HistorialClinico historialClinico) {
        return historialClinico != null ? historialClinico.getHistorialClinicoId() : null;
    }

    default HistorialClinico mapHistorialClinico(Long historialClinicoId) {
        if (historialClinicoId != null) {
            HistorialClinico historial = new HistorialClinico();
            historial.setHistorialClinicoId(historialClinicoId);
            return historial;
        }
        return null;
    }
}