package com.gestionvet.gestionvet.domain.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ClienteDTO {
    private Long id;
    private Long personaId;
    private Boolean esPropietario;
    private Long propietarioId; // Opcional, solo si el cliente tiene un propietario asociado
}
