package com.gestVet.app.persistence.mapper;

import com.gestVet.app.domain.dto.AdministradorDTO;
import com.gestVet.app.persistence.entity.Administrador;
import com.gestVet.app.persistence.entity.Privilegio;
import com.gestVet.app.persistence.entity.Usuario;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.InheritInverseConfiguration;

@Mapper(componentModel = "spring")
public interface AdministradorMapper {

    // Mapeo de Administrador a AdministradorDTO
    @Mapping(source = "id", target = "administradorId")
    @Mapping(source = "usuario", target = "usuarioId")
    @Mapping(source = "privilegio", target = "privilegioId")
    AdministradorDTO toDto(Administrador administrador);

    // Mapeo inverso de AdministradorDTO a Administrador
    @InheritInverseConfiguration
    @Mapping(target = "usuario", source = "usuarioId")
    @Mapping(target = "privilegio", source = "privilegioId")
    Administrador toEntity(AdministradorDTO administradorDTO);

    // Método de mapeo explícito para convertir Usuario a Long (su id)
    default Long map(Usuario usuario) {
        if (usuario != null) {
            return usuario.getUsuarioId();
        }
        return null;
    }

    // Método de mapeo explícito para convertir Long (id) a Usuario
    default Usuario mapUsuario(Long usuarioId) {
        if (usuarioId != null) {
            Usuario usuario = new Usuario();
            usuario.setUsuarioId(usuarioId);
            return usuario;
        }
        return null;
    }

    // Método de mapeo explícito para convertir Privilegio a Long (su id)
    default Long map(Privilegio privilegio) {
        if (privilegio != null) {
            return privilegio.getPrivilegioId();
        }
        return null;
    }

    // Método de mapeo explícito para convertir Long (id) a Privilegio
    default Privilegio mapPrivilegio(Long privilegioId) {
        if (privilegioId != null) {
            Privilegio privilegio = new Privilegio();
            privilegio.setPrivilegioId(privilegioId);
            return privilegio;
        }
        return null;
    }
}
