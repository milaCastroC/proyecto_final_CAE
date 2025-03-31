package com.gestVet.app.persistence.mapper;

import com.gestVet.app.domain.dto.VeterinarioDTO;
import com.gestVet.app.persistence.entity.*;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.InheritInverseConfiguration;

@Mapper(componentModel = "spring")
public interface VeterinarioMapper {

    // Mapeo de Veterinario a VeterinarioDTO
    @Mapping(source = "veterinarioId", target = "veterinarioId")
    @Mapping(source = "especialidad", target = "especialidad")
    @Mapping(source = "tarjetaProfesional", target = "tarjetaProfesional")
    @Mapping(source = "usuario", target = "usuarioId")
    VeterinarioDTO toDto(Veterinario veterinario);

    // Mapeo inverso de VeterinarioDTO a Veterinario
    @InheritInverseConfiguration
    @Mapping(target = "usuario", source = "usuarioId")
    @Mapping(target = "citas", ignore = true)
    @Mapping(target = "veterinarioHorarios", ignore = true)
    Veterinario toEntity(VeterinarioDTO veterinarioDTO);

    // Métodos de conversión para las relaciones

    default Long mapUsuario(Usuario usuario) {
        return usuario != null ? usuario.getUsuarioId() : null;
    }

    default Usuario mapUsuario(Long usuarioId) {
        if (usuarioId != null) {
            Usuario usuario = new Usuario();
            usuario.setUsuarioId(usuarioId);
            return usuario;
        }
        return null;
    }
}