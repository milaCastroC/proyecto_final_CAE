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
public class AdministradorDTO {
    private Long administradorId;

    @NotNull(message = "El usuario es obligatorio")
    private Long usuarioId;

    @NotNull(message = "El cargo es obligatorio")
    private String cargo;

    public AdministradorDTO(Long administradorId, Long usuarioId, String cargo) {
        this.administradorId = administradorId;
        this.usuarioId = usuarioId;
        this.cargo = cargo;
    }
}
