package com.gestVet.app.domain.dto;

import lombok.Getter;
import lombok.Setter;
import java.time.LocalDate;
import java.util.List;

@Data
@Getter
@Setter
@NoArgsConstructor
public class FacturaDTO {
    private Long facturaId;

    @NotNull("El total es obligatorio")
    private Double total;

    @NotNull("El estado es obligatorio")
    private String estado;

    @NotNull("El método de pago es obligatorio")
    private String metodoPago;

    @NotNull("La fecha de emisión es obligatoria")
    private LocalDate fechaEmision;

    public FacturaDTO(Long facturaId, Double total, String estado, String metodoPago, LocalDate fechaEmision) {
        this.facturaId = facturaId;
        this.total = total;
        this.estado = estado;
        this.metodoPago = metodoPago;
        this.fechaEmision = fechaEmision;
    }
}
