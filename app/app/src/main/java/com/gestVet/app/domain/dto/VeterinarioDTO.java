package com.gestionvet.gestionvet.domain.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class VeterinarioDTO {
    private Long id;
    private String especialidad;
    private String tarjetaProfesional;
    private Long usuarioId; // Referencia al usuario asociado
}
