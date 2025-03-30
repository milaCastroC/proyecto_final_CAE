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
public class PersonaDTO {
    private Long personaId;

    @NotNull(message = "La identificación es obligatoria")
    private Long identificacion;

    @NotNull(message = "El nombre es obligatorio")
    private String nombre;

    @NotNull(message = "El apellido es obligatorio")
    private String apellido;

    private String telefono;

    private String email;

    private String direccion;

    private Long clienteId;

    private Long usuarioId;

    public PersonaDTO(Long personaId, Long identificacion, String nombre, String apellido, String telefono, String email, String direccion, Long clienteId, Long usuarioId) {
        this.personaId = personaId;
        this.identificacion = identificacion;
        this.nombre = nombre;
        this.apellido = apellido;
        this.telefono = telefono;
        this.email = email;
        this.direccion = direccion;
        this.clienteId = clienteId;
        this.usuarioId = usuarioId;
    }
}
