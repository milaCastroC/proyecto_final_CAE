package com.gestVet.app.domain.dto;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.time.LocalDate;

import jakarta.validation.constraints.NotNull;

@Data
@Getter
@Setter
@NoArgsConstructor
public class FacturaDTO {
    private Long facturaId;

    @NotNull(message = "El total es obligatorio")
    private Double total;

    @NotNull(message = "El estado es obligatorio")
    private String estado;

    @NotNull(message = "El método de pago es obligatorio")
    private String metodoPago;

    @NotNull(message = "La fecha de emisión es obligatoria")
    private LocalDate fechaEmision;

    public FacturaDTO(Long facturaId, Double total, String estado, String metodoPago, LocalDate fechaEmision) {
        this.facturaId = facturaId;
        this.total = total;
        this.estado = estado;
        this.metodoPago = metodoPago;
        this.fechaEmision = fechaEmision;
    }
}
