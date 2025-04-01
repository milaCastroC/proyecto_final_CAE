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
public class ClienteDTO {
    private Long clienteId;

    @NotNull(message = "La persona es obligatoria")
    private Long personaId;

    @NotNull(message = "Es obligatorio decir si es o no propietario")
    private Boolean esPropietario;

    public ClienteDTO(Long clienteId, Long personaId, Boolean esPropietario) {
        this.clienteId = clienteId;
        this.personaId = personaId;
        this.esPropietario = esPropietario;
    }
}
