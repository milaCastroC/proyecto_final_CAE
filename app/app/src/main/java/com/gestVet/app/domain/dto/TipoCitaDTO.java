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
public class TipoCitaDTO {
    private Long tipoCitaId;

    @NotNull(message = "El nombre es obligatorio")
    private String nombre;

    public TipoCitaDTO(Long tipoCitaId, String nombre) {
        this.tipoCitaId = tipoCitaId;
        this.nombre = nombre;
    }
}
