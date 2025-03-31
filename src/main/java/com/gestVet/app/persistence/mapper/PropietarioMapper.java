package com.gestVet.app.persistence.mapper;

import com.gestVet.app.domain.dto.PropietarioDTO;
import com.gestVet.app.persistence.entity.*;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.InheritInverseConfiguration;

@Mapper(componentModel = "spring")
public interface PropietarioMapper {

    // Mapeo de Propietario a PropietarioDTO
    @Mapping(source = "propietarioId", target = "propietarioId")
    @Mapping(source = "cliente", target = "clienteId")
    PropietarioDTO toDto(Propietario propietario);

    // Mapeo inverso de PropietarioDTO a Propietario
    @InheritInverseConfiguration
    @Mapping(target = "cliente", source = "clienteId")
    @Mapping(target = "mascotas", ignore = true)
    Propietario toEntity(PropietarioDTO propietarioDTO);

    // Métodos de conversión para las relaciones

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
}