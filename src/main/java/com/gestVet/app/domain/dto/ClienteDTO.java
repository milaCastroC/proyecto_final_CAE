package com.gestVet.app.domain.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
public class ClienteDTO extends PersonaDTO{

    @NotNull(message = "Es obligatorio decir si es o no propietario")
    private Boolean esPropietario;

    public ClienteDTO(Long personaId, String identificacion, String tipoIdentificacion, String nombre, String apellido, String telefono, String email, String direccion, Boolean esPropietario) {
        super(personaId, identificacion, tipoIdentificacion, nombre, apellido, telefono, email, direccion);
        this.esPropietario = esPropietario;
    }
}
