package com.gestVet.app.persistence.mapper;

import com.gestVet.app.domain.dto.UsuarioDTO;
import com.gestVet.app.persistence.entity.*;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.InheritInverseConfiguration;

@Mapper(componentModel = "spring")
public interface UsuarioMapper {

    // Mapeo de Usuario a UsuarioDTO
    @Mapping(source = "usuarioId", target = "usuarioId")
    @Mapping(source = "username", target = "username")
    @Mapping(source = "password", target = "password")
    @Mapping(source = "rol", target = "rol")
    @Mapping(source = "persona", target = "personaId")
    @Mapping(source = "administrador", target = "administradorId")
    @Mapping(source = "veterinario", target = "veterinarioId")
    UsuarioDTO toDto(Usuario usuario);

    // Mapeo inverso de UsuarioDTO a Usuario
    @InheritInverseConfiguration
    @Mapping(target = "persona", source = "personaId")
    @Mapping(target = "administrador", source = "administradorId")
    @Mapping(target = "veterinario", source = "veterinarioId")
    Usuario toEntity(UsuarioDTO usuarioDTO);

    // Métodos de conversión para las relaciones

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
    
    default Long mapAdministrador(Administrador administrador) {
        return administrador != null ? administrador.getId() : null;
    }

    default Administrador mapAdministrador(Long administradorId) {
        if (administradorId != null) {
            Administrador administrador = new Administrador();
            administrador.setId(administradorId);
            return administrador;
        }
        return null;
    }

    default Long mapVeterinario(Veterinario veterinario) {
        return veterinario != null ? veterinario.getId() : null;
    }

    default Veterinario mapVeterinario(Long veterinarioId) {
        if (veterinarioId != null) {
            Veterinario veterinario = new Veterinario();
            veterinario.setId(veterinarioId);
            return veterinario;
        }
        return null;
    }
}