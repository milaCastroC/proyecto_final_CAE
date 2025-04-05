package com.gestVet.app.domain.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class AdministradorDTO extends UsuarioDTO{

    @NotNull(message = "El cargo es obligatorio")
    private String cargo;

    @NotNull(message = "El area es obligatorio")
    private String area;

    public AdministradorDTO(Long personaId, String identificacion, String tipoIdentificacion, String nombre, String apellido, String telefono, String email, String direccion, String username, String cargo) {
        super(personaId, identificacion, tipoIdentificacion, nombre, apellido, telefono, email, direccion, username);
        this.cargo = cargo;
    }
}
