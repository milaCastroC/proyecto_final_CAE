package com.gestVet.app.domain.dto;

import lombok.Getter;
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
