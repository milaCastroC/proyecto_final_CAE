package com.gestVet.app.persistence.mapper;

import com.gestVet.app.domain.dto.AdministradorDTO;
import com.gestVet.app.persistence.entity.Administrador;
import com.gestVet.app.persistence.entity.Usuario;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.InheritInverseConfiguration;

@Mapper(componentModel = "spring", uses = UsuarioMapper.class)
public interface AdministradorMapper {

    // Mapeo Administrador a AdministradorDTO
    @Mapping(source = "personaId", target = "personaId") // Heredado de Usuario/Persona
    @Mapping(source = "cargo", target = "cargo")
    @Mapping(source = "area", target = "area")
    AdministradorDTO toDto(Administrador administrador);

    // Mapeo inverso de AdministradorDTO a Administrador
    @InheritInverseConfiguration
    Administrador toEntity(AdministradorDTO administradorDTO);

//    // Método de mapeo explícito para convertir Usuario a Long (su id)
//    default Long map(Usuario usuario) {
//        if (usuario != null) {
//            return usuario.getUsuarioId();
//        }
//        return null;
//    }
//
//    // Método de mapeo explícito para convertir Long (id) a Usuario
//    default Usuario mapUsuario(Long usuarioId) {
//        if (usuarioId != null) {
//            Usuario usuario = new Usuario();
//            usuario.setUsuarioId(usuarioId);
//            return usuario;
//        }
//        return null;
//    }

}
