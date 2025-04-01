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

    @NotNull(message = "El privilegio es obligatorio")
    private Long privilegioId;

    @NotNull(message = "El cargo es obligatorio")
    private String cargo;

    public AdministradorDTO(Long administradorId, Long usuarioId, Long privilegioId, String cargo) {
        this.administradorId = administradorId;
        this.usuarioId = usuarioId;
        this.privilegioId = privilegioId;
        this.cargo = cargo;
    }
}
