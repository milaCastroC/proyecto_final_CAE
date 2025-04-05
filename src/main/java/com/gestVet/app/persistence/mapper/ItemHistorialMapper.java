package com.gestVet.app.persistence.mapper;

import com.gestVet.app.domain.dto.ItemHistorialDTO;
import com.gestVet.app.persistence.entity.*;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.InheritInverseConfiguration;

import java.util.List;

@Mapper(componentModel = "spring", uses = {MascotaMapper.class})
public interface ItemHistorialMapper {

    @Mapping(source = "itemHistorialId", target = "itemHistorialId")
    @Mapping(source = "mascota.mascotaId", target = "mascotaId")
    @Mapping(source = "fecha", target = "fecha")
    @Mapping(source = "diagnostico", target = "diagnostico")
    @Mapping(source = "tratamiento", target = "tratamiento")
    @Mapping(source = "observaciones", target = "observaciones")
    @Mapping(source = "tipo", target = "tipo")
    @Mapping(source = "citaId", target = "citaId")
    @Mapping(source = "tipoCita", target = "tipoCita")
    ItemHistorialDTO toDto(ItemHistorial itemHistorial);

    List<ItemHistorialDTO> toDtoList(List<ItemHistorial> itemHistorialList);

    @InheritInverseConfiguration
    @Mapping(target = "mascota", source = "mascotaId")
    ItemHistorial toEntity(ItemHistorialDTO itemHistorialDTO);
    
    @Mapping(target = "itemHistorialId", ignore = true)
    void updateEntityFromDto(ItemHistorialDTO dto, @MappingTarget ItemHistorial entity);

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