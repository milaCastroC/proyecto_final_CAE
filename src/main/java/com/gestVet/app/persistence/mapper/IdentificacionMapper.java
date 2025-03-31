package com.gestVet.app.persistence.mapper;

import com.gestVet.app.domain.dto.IdentificacionDTO;
import com.gestVet.app.persistence.entity.Identificacion;
import com.gestVet.app.persistence.entity.Persona;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.InheritInverseConfiguration;

@Mapper(componentModel = "spring")
public interface IdentificacionMapper {

    // Mapeo de Identificacion a IdentificacionDTO
    @Mapping(source = "identificacion", target = "identificacionId")
    @Mapping(source = "tipo", target = "tipo")
    @Mapping(source = "estado", target = "estado")
    @Mapping(source = "persona", target = "personaId")
    IdentificacionDTO toDto(Identificacion identificacion);

    // Mapeo inverso de IdentificacionDTO a Identificacion
    @InheritInverseConfiguration
    @Mapping(target = "persona", source = "personaId")
    Identificacion toEntity(IdentificacionDTO identificacionDTO);

    // Métodos de conversión para las relaciones
    default Long mapPersona(Persona persona) {
        return persona != null ? persona.getPersonaId() : null;
    }

    default Persona mapPersona(Long personaId) {
        if (personaId != null) {
            Persona persona = new Persona();
            persona.setPersonaId(personaId);
            return persona;
        }
        return null;
    }
}