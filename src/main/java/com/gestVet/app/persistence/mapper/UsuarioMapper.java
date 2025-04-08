package com.gestVet.app.persistence.mapper;

import com.gestVet.app.domain.dto.UsuarioDTO;
import com.gestVet.app.persistence.entity.*;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.InheritInverseConfiguration;

@Mapper(componentModel = "spring", uses = PersonaMapper.class)
public interface UsuarioMapper {

    // Mapeo de Usuario a UsuarioDTO
    @Mapping(source = "personaId", target = "personaId") // PK heredada
    @Mapping(source = "username", target = "username")
    @Mapping(source = "password", target = "password")
//    @Mapping(source = "rol", target = "rol")
    UsuarioDTO toDto(Usuario usuario);

    // Mapeo inverso de UsuarioDTO a Usuario
    @InheritInverseConfiguration
    Usuario toEntity(UsuarioDTO usuarioDTO);

    // Métodos de conversión para las relaciones
//
//    default Long mapPersona(Persona persona) {
//        return persona != null ? persona.getPersonaId() : null;
//    }
//
//    default Persona mapPersona(Long personaId) {
//        if (personaId != null) {
//            Persona persona = new Persona();
//            persona.setPersonaId(personaId);
//            return persona;
//        }
//        return null;
//    }
//
//    default Long mapAdministrador(Administrador administrador) {
//        return administrador != null ? administrador.getId() : null;
//    }
//
//    default Administrador mapAdministrador(Long administradorId) {
//        if (administradorId != null) {
//            Administrador administrador = new Administrador();
//            administrador.setId(administradorId);
//            return administrador;
//        }
//        return null;
//    }
//
//    default Long mapVeterinario(Veterinario veterinario) {
//        return veterinario != null ? veterinario.getVeterinarioId() : null;
//    }
//
//    default Veterinario mapVeterinario(Long veterinarioId) {
//        if (veterinarioId != null) {
//            Veterinario veterinario = new Veterinario();
//            veterinario.setVeterinarioId(veterinarioId);
//            return veterinario;
//        }
//        return null;
//    }
}