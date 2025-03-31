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
public class VeterinarioHorarioDTO {
    private Long veterinarioHorarioId;

    @NotNull(message = "El veterinario es obligatorio")
    private Long veterinarioId;

    @NotNull(message = "El horario es obligatorio")
    private Long horarioId;

    public VeterinarioHorarioDTO(Long veterinarioHorarioId, Long veterinarioId, Long horarioId) {
        this.veterinarioHorarioId = veterinarioHorarioId;
        this.veterinarioId = veterinarioId;
        this.horarioId = horarioId;
    }
}
