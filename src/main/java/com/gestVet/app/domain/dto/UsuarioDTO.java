package com.gestVet.app.domain.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UsuarioDTO extends PersonaDTO{

    @NotBlank(message = "El username es obligatorio")
    private String username;

    @NotBlank(message = "La contraseña es obligatoria")
    private String password;

    @Pattern(
            regexp = "ADMINISTRADOR|VETERINARIO|Administrador|Veterinario|administrador|veterinario",
            message = "El rol debe ser ADMINISTRADOR o VETERINARIO"
    )
    private String rol;

    public UsuarioDTO(Long personaId, String identificacion, String tipoIdentificacion, String nombre, String apellido, String telefono, String email, String direccion, String username) {
        super(personaId, identificacion, tipoIdentificacion, nombre, apellido, telefono, email, direccion);
        this.username = username;
    }
}
