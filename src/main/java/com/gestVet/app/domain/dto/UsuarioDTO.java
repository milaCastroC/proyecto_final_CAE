package com.gestVet.app.domain.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UsuarioDTO extends PersonaDTO{

    @NotNull(message = "El username es obligatorio")
    private String username;

    @NotNull(message = "La contraseña es obligatoria")
    private String password;

    @NotNull(message = "El rol es obligatorio")
    private String rol;

    public UsuarioDTO(Long personaId, String identificacion, String tipoIdentificacion, String nombre, String apellido, String telefono, String email, String direccion, String username) {
        super(personaId, identificacion, tipoIdentificacion, nombre, apellido, telefono, email, direccion);
        this.username = username;
    }
}
