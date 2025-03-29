package com.gestionvet.gestionvet.domain.dto;

import lombok.Getter;
import lombok.Setter;
import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
public class FacturaDTO {
    private Long id;
    private Double total;
    private String estado;
    private String metodoPago;
    private LocalDate fechaEmision;
    private List<Long> clienteIds; // Lista de clientes asociados a la factura
}
