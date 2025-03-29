package com.gestionvet.gestionvet.domain.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UsuarioDTO {
    private Long id;
    private String username;
    private String rol;
    private Long personaId; // Referencia a la persona asociada
}
