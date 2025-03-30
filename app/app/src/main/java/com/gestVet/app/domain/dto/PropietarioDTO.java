package com.gestVet.app.domain.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Data
@Getter
@Setter
@NoArgsConstructor
public class PropietarioDTO {
    private Long propietarioId;

    @NotNull(message = "El cliente es obligatorio")
    private Long clienteId;

    public PropietarioDTO(Long propietarioId, Long clienteId) {
        this.propietarioId = propietarioId;
        this.clienteId = clienteId;
    };
}
