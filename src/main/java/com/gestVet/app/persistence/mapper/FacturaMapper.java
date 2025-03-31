package com.gestVet.app.persistence.mapper;

import com.gestVet.app.domain.dto.FacturaDTO;
import com.gestVet.app.persistence.entity.Factura;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.InheritInverseConfiguration;

@Mapper(componentModel = "spring")
public interface FacturaMapper {

    // Mapeo de Factura a FacturaDTO
    @Mapping(source = "facturaId", target = "facturaId")
    @Mapping(source = "total", target = "total")
    @Mapping(source = "estado", target = "estado")
    @Mapping(source = "metodoPago", target = "metodoPago")
    @Mapping(source = "fechaEmision", target = "fechaEmision")
    FacturaDTO toDto(Factura factura);

    // Mapeo inverso de FacturaDTO a Factura
    @InheritInverseConfiguration
    @Mapping(target = "clienteFacturas", ignore = true)
    Factura toEntity(FacturaDTO facturaDTO);
}