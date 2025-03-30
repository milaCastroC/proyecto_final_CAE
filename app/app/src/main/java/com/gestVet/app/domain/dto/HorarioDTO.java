package com.gestVet.app.domain.dto;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.time.LocalDateTime;


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
    private LocalDateTime horaInicio;

    @NotNull(message = "La hora de fin es obligatoria")
    @Future(message = "La hora de fin debe ser una fecha futura")
    private LocalDateTime horaFin;

    public HorarioDTO(Long horarioId, LocalDateTime horaInicio, LocalDateTime horaFin) {
        this.horarioId = horarioId;
        this.horaInicio = horaInicio;
        this.horaFin = horaFin;
    }
}
