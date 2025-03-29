package com.gestionvet.gestionvet.domain.dto;

import lombok.Getter;
import lombok.Setter;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
public class HorarioDTO {
    private Long id;
    private LocalDateTime horaInicio;
    private LocalDateTime horaFin;
    private List<Long> citaIds; // Lista de IDs de citas asociadas
    private List<Long> veterinarioHorarioIds; // Lista de IDs de veterinarios asociados al horario
}
