package com.gestVet.app.domain.dto;

import lombok.Getter;
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
