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
public class IdentificacionDTO {
    private Long identificacionId;

    @NotNull(message = "El tipo es obligatorio")
    private String tipo;

    @NotNull(message = "El estado es obligatorio")
    private Boolean estado;

    @NotNull(message = "La persona es obligatoria")
    private Long personaId;

    public IdentificacionDTO(Long identificacionId, String tipo, Boolean estado, Long personaId) {
        this.identificacionId = identificacionId;
        this.tipo = tipo;
        this.estado = estado;
        this.personaId = personaId;
    }
}
