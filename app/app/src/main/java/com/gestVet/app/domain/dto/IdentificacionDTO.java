package com.gestionvet.gestionvet.domain.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class IdentificacionDTO {
    private Long id;
    private String tipo;
    private Boolean estado;
    private Long personaId; 
}
