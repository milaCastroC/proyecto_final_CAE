package com.gestVet.app.domain.dto;

import lombok.Getter;
import lombok.Setter;
import java.time.LocalDate;

@Data
@Getter
@Setter
@NoArgsConstructor
public class ItemHistorialDTO {
    private Long itemHistorialId;

    @NotNull(message = "El historial clínico es obligatorio")
    private Long historialClinicoId;

    @NotNull(message = "La fecha es obligatoria")
    private LocalDate fecha;

    @NotNull(message = "El diagnóstico es obligatorio")
    private String diagnostico;

    @NotNull(message = "El tratamiento es obligatorio")
    private String tratamiento;

    private String observaciones;

    private String tipos;

    public ItemHistorialDTO(Long itemHistorialId, Long historialClinicoId, LocalDate fecha, String diagnostico, String tratamiento, String observaciones, String tipos) {
        this.itemHistorialId = itemHistorialId;
        this.historialClinicoId = historialClinicoId;
        this.fecha = fecha;
        this.diagnostico = diagnostico;
        this.tratamiento = tratamiento;
        this.observaciones = observaciones;
        this.tipos = tipos;
    }
}
