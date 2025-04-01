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
    @Mapping(source = "mascota", target = "mascotaId")
    @Mapping(source = "fecha", target = "fecha")
    @Mapping(source = "diagnostico", target = "diagnostico")
    @Mapping(source = "tratamiento", target = "tratamiento")
    @Mapping(source = "observaciones", target = "observaciones")
    @Mapping(source = "tipo", target = "tipo")
    ItemHistorialDTO toDto(ItemHistorial itemHistorial);

    // Mapeo inverso de ItemHistorialDTO a ItemHistorial
    @InheritInverseConfiguration
    ItemHistorial toEntity(ItemHistorialDTO itemHistorialDTO);

    default Long mapmascota(Mascota mascota) {
        return mascota != null ? mascota.getMascotaId() : null;
    }

    default Mascota mapmascota(Long mascotaId) {
        if (mascotaId != null) {
            Mascota mascota = new Mascota();
            mascota.setMascotaId(mascotaId);
            return mascota;
        }
        return null;
    }
}