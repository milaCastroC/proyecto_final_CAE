package com.gestVet.app.persistence.mapper;

import com.gestVet.app.domain.dto.PersonaDTO;
import com.gestVet.app.persistence.entity.*;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.InheritInverseConfiguration;

@Mapper(componentModel = "spring")
public interface PersonaMapper {

    // Mapeo de Persona a PersonaDTO
    @Mapping(source = "personaId", target = "personaId")
    @Mapping(source = "tipoIdentificacion", target = "tipoIdentificacion")
    @Mapping(source = "identificacion", target = "identificacion")
    @Mapping(source = "nombre", target = "nombre")
    @Mapping(source = "apellido", target = "apellido")
    @Mapping(source = "telefono", target = "telefono")
    @Mapping(source = "email", target = "email")
    @Mapping(source = "direccion", target = "direccion")
    @Mapping(source = "cliente", target = "clienteId")
    @Mapping(source = "usuario", target = "usuarioId")
    PersonaDTO toDto(Persona persona);

    // Mapeo inverso de PersonaDTO a Persona
    @InheritInverseConfiguration
    @Mapping(target = "cliente", source = "clienteId")
    @Mapping(target = "usuario", source = "usuarioId")
    Persona toEntity(PersonaDTO personaDTO);

    default Long mapCliente(Cliente cliente) {
        return cliente != null ? cliente.getClienteId() : null;
    }

    default Cliente mapCliente(Long clienteId) {
        if (clienteId != null) {
            Cliente cliente = new Cliente();
            cliente.setClienteId(clienteId);
            return cliente;
        }
        return null;
    }

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