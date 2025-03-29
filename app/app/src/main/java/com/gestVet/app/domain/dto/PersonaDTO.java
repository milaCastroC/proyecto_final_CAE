package com.gestionvet.gestionvet.domain.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PersonaDTO {
    private Long id;
    private Long identificacionId;
    private String nombre;
    private String apellido;
    private String telefono;
    private String email;
    private String direccion;
    private Long clienteId;
    private Long usuarioId;
}
