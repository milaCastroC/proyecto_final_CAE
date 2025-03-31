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
public class UsuarioDTO {
    private Long usuarioId;

    @NotNull(message = "El username es obligatorio")
    private String username;

    @NotNull(message = "La contraseña es obligatoria")
    private String password;

    @NotNull(message = "El rol es obligatorio")
    private String rol;

    @NotNull(message = "La persona es obligatoria")
    private Long personaId;

    private Long administradorId;

    private Long veterinarioId;

    public UsuarioDTO(Long usuarioId, String username, String password, String rol, Long personaId, Long administradorId, Long veterinarioId) {
        this.usuarioId = usuarioId;
        this.username = username;
        this.password = password;
        this.rol = rol;
        this.personaId = personaId;
        this.administradorId = administradorId;
        this.veterinarioId = veterinarioId;
    }
}
