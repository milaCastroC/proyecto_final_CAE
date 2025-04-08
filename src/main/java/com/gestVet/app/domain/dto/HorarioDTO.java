package com.gestVet.app.domain.dto;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.time.LocalTime;


import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;

@Data
@Getter
@Setter
@NoArgsConstructor
public class HorarioDTO {

    private Long horarioId;

    @NotNull(message = "La hora de inicio es obligatoria")
    @Future(message = "La hora de inicio debe ser una fecha futura")
    private LocalTime horaInicio;

    @NotNull(message = "La hora de fin es obligatoria")
    @Future(message = "La hora de fin debe ser una fecha futura")
    private LocalTime horaFin;

    public HorarioDTO(Long horarioId, LocalTime horaInicio, LocalTime horaFin) {
        this.horarioId = horarioId;
        this.horaInicio = horaInicio;
        this.horaFin = horaFin;
    }
}
