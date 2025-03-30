package com.gestVet.app.persistence.mapper;

import com.gestVet.app.domain.dto.ClienteFacturaDTO;
import com.gestVet.app.persistence.entity.*;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.InheritInverseConfiguration;

@Mapper(componentModel = "spring")
public interface ClienteFacturaMapper {

    // Mapeo de ClienteFactura a ClienteFacturaDTO
    @Mapping(source = "clienteFacturaId", target = "clienteFacturaId")
    @Mapping(source = "cliente", target = "clienteId")
    @Mapping(source = "factura", target = "facturaId")
    ClienteFacturaDTO toDto(ClienteFactura clienteFactura);

    // Mapeo inverso de ClienteFacturaDTO a ClienteFactura
    @InheritInverseConfiguration
    @Mapping(target = "cliente", source = "clienteId")
    @Mapping(target = "factura", source = "facturaId")
    ClienteFactura toEntity(ClienteFacturaDTO clienteFacturaDTO);

    // Métodos de mapeo para las relaciones

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

    default Long mapFactura(Factura factura) {
        return factura != null ? factura.getFacturaId() : null;
    }

    default Factura mapFactura(Long facturaId) {
        if (facturaId != null) {
            Factura factura = new Factura();
            factura.setFacturaId(facturaId);
            return factura;
        }
        return null;
    }
}