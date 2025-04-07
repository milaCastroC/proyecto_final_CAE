package com.gestVet.app.domain.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class VeterinarioDTO extends UsuarioDTO{

    @NotNull(message = "La especialidad es obligatoria")
    private String especialidad;

    @NotNull(message = "La tarjeta profesional es obligatoria")
    private String tarjetaProfesional;

    public VeterinarioDTO(Long personaId, String identificacion, String tipoIdentificacion, String nombre, String apellido, String telefono, String email, String direccion, String username, String especialidad) {
        super(personaId, identificacion, tipoIdentificacion, nombre, apellido, telefono, email, direccion, username);
        this.especialidad = especialidad;
    }

}
