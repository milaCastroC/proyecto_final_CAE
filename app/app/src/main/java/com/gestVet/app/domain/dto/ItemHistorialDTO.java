package com.gestionvet.gestionvet.domain.dto;

import lombok.Getter;
import lombok.Setter;
import java.time.LocalDate;

@Getter
@Setter
public class ItemHistorialDTO {
    private Long id;
    private Long historialClinicoId;
    private LocalDate fecha;
    private String diagnostico;
    private String tratamiento;
    private String observaciones;
    private String tipos;
}
