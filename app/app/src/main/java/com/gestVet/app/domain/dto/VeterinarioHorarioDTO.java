package com.gestionvet.gestionvet.domain.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class VeterinarioHorarioDTO {
    private Long id;
    private Long veterinarioId; // Referencia al veterinario
    private Long horarioId; // Referencia al horario
}
