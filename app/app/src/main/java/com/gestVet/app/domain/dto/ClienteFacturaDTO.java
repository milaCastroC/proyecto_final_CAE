package com.gestionvet.gestionvet.domain.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ClienteFacturaDTO {
    private Long id;
    private Long clienteId;
    private Long facturaId;
}
