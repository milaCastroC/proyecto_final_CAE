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
public class ItemHistorialDTO {
    private Long itemHistorialId;

    @NotNull(message = "El historial id de mascota es obligatorio")
    private Long mascotaId;

    @NotNull(message = "La fecha es obligatoria")
    private LocalDate fecha;

    @NotNull(message = "El diagnóstico es obligatorio")
    private String diagnostico;

    @NotNull(message = "El tratamiento es obligatorio")
    private String tratamiento;

    private String observaciones;

    private String tipo;

    public ItemHistorialDTO(Long itemHistorialId, Long mascotaId, LocalDate fecha, String diagnostico, String tratamiento, String observaciones, String tipo) {
        this.itemHistorialId = itemHistorialId;
        this.mascotaId = mascotaId;
        this.fecha = fecha;
        this.diagnostico = diagnostico;
        this.tratamiento = tratamiento;
        this.observaciones = observaciones;
        this.tipo = tipo;
    }
}
