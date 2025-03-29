package com.gestionvet.gestionvet.domain.dto;

import lombok.Getter;
import lombok.Setter;
import java.time.LocalDateTime;

@Getter
@Setter
public class CitaDTO {
    private Long id;
    private Long mascotaId;
    private Long horarioId;
    private Long veterinarioId;
    private LocalDateTime fecha;
    private Long tipoCitaId;
    private String estado;
}
