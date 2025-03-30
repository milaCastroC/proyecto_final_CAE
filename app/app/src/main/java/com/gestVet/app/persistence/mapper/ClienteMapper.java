package com.gestVet.app.persistence.mapper;

import com.gestVet.app.domain.dto.ClienteDTO;
import com.gestVet.app.persistence.entity.*;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.InheritInverseConfiguration;

@Mapper(componentModel = "spring")
public interface ClienteMapper {

    // Mapeo de Cliente a ClienteDTO
    @Mapping(source = "clienteId", target = "clienteId")
    @Mapping(source = "persona", target = "personaId")
    @Mapping(source = "esPropietario", target = "esPropietario")
    @Mapping(source = "propietario", target = "propietarioId")
    ClienteDTO toDto(Cliente cliente);

    // Mapeo inverso de ClienteDTO a Cliente
    @InheritInverseConfiguration
    @Mapping(target = "persona", source = "personaId")
    @Mapping(target = "propietario", source = "propietarioId")
    @Mapping(target = "clienteFacturas", ignore = true)
    Cliente toEntity(ClienteDTO clienteDTO);

    // Métodos de mapeo para las relaciones

    default Long mapPersona(Persona persona) {
        return persona != null ? persona.getId() : null;
    }

    default Persona mapPersona(Long personaId) {
        if (personaId != null) {
            Persona persona = new Persona();
            persona.setId(personaId);
            return persona;
        }
        return null;
    }

    default Long mapPropietario(Propietario propietario) {
        return propietario != null ? propietario.getId() : null;
    }

    default Propietario mapPropietario(Long propietarioId) {
        if (propietarioId != null) {
            Propietario propietario = new Propietario();
            propietario.setId(propietarioId);
            return propietario;
        }
        return null;
    }
}