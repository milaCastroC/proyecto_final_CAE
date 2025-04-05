package com.gestVet.app.persistence.mapper;

import com.gestVet.app.domain.dto.MascotaDTO;
import com.gestVet.app.persistence.entity.*;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.InheritInverseConfiguration;

@Mapper(componentModel = "spring")
public interface MascotaMapper {

    // Mapeo de Mascota a MascotaDTO 
    @Mapping(source = "mascotaId", target = "mascotaId")
    @Mapping(source = "propietario", target = "propietarioId")
    @Mapping(source = "nombre", target = "nombre")
    @Mapping(source = "especie", target = "especie")
    @Mapping(source = "raza", target = "raza")
    @Mapping(source = "sexo", target = "sexo")
    @Mapping(source = "fechaNacimiento", target = "fechaNacimiento")
    @Mapping(source = "edad", target = "edad")
    @Mapping(source = "peso", target = "peso")
    MascotaDTO toDto(Mascota mascota);

    // Mapeo inverso de MascotaDTO a Mascota
    @InheritInverseConfiguration
    @Mapping(target = "mascotaId", source = "mascotaId")
    @Mapping(target = "propietario", source = "propietarioId")
    @Mapping(target = "citas", ignore = true)
    @Mapping(target = "historialClinico", ignore = true)
    Mascota toEntity(MascotaDTO mascotaDTO);

    // Métodos de conversión para las relaciones
    default Long mapCliente(Cliente Cliente) {
        return Cliente != null ? Cliente.getPersonaId() : null;
    }

    default Cliente mapCliente(Long ClienteId) {
        if (ClienteId != null) {
            Cliente Cliente = new Cliente();
            Cliente.setPersonaId(ClienteId);
            return Cliente;
        }
        return null;
    }
}