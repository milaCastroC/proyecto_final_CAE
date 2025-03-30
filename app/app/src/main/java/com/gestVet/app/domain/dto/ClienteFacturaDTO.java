package com.gestVet.app.domain.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@Getter
@Setter
@NoArgsConstructor
public class ClienteFacturaDTO {
    private Long clienteFacturaId;

    @NotNull(message = "El cliente es obligatorio")
    private Long clienteId;

    @NotNull(message = "La factura es obligatoria")
    private Long facturaId;

    public ClienteFacturaDTO(Long clienteFacturaId, Long clienteId, Long facturaId) {
        this.clienteFacturaId = clienteFacturaId;
        this.clienteId = clienteId;
        this.facturaId = facturaId;
    }
}
