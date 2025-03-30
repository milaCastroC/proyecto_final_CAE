package com.gestVet.app.domain.dto;

import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
@NoArgsConstructor
public class ClienteFacturaDTO {
    private Long clienteFacturaId;

    @NotNull("El cliente es obligatorio")
    private Long clienteId;

    @NotNull("La factura es obligatoria")
    private Long facturaId;

    public ClienteFacturaDTO(Long clienteFacturaId, Long clienteId, Long facturaId) {
        this.clienteFacturaId = clienteFacturaId;
        this.clienteId = clienteId;
        this.facturaId = facturaId;
    }
}
