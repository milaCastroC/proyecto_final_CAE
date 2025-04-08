package com.gestVet.app.domain.dto;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

@Data
@Getter
@Setter
@NoArgsConstructor
public class CitaDTO {
    private Long citaId;

    @NotNull(message = "La mascota es obligatoria")
    private Long mascotaId;

    @NotNull(message = "El horario es obligatorio")
    private Long horarioId;

    @NotNull(message = "El veterinario es obligatorio")
    private Long veterinarioId;

    @NotNull(message = "La fecha es obligatoria")
    @Future(message = "La fecha debe ser en el futuro")
    private LocalDate fecha;

    @NotNull(message = "El tipo de cita es obligatoria")
    private String tipoCita;

    @NotBlank(message = "El estado es obligatorio")
    private String estado;

    public CitaDTO(Long citaId, Long mascotaId, Long horarioId, Long veterinarioId, LocalDate fecha, String tipoCita, String estado) {
        this.citaId = citaId;
        this.mascotaId = mascotaId;
        this.horarioId = horarioId;
        this.veterinarioId = veterinarioId;
        this.fecha = fecha;
        this.tipoCita = tipoCita;
        this.estado = estado;
    }
}
