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
public class PrivilegioDTO {
    private Long privilegioId;

    @NotNull(message = "El nombre es obligatorio")
    private String nombre;

    public PrivilegioDTO(Long privilegioId, String nombre) {
        this.privilegioId = privilegioId;
        this.nombre = nombre;
    }
}
