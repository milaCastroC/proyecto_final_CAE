package com.gestVet.app.domain.dto;

import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
@NoArgsConstructor
public class ClienteDTO {
    private Long clienteId;

    @NotNull("La persona es obligatoria")
    private Long personaId;

    @NotNull("Es obligatorio decir si es o no propietario")
    private Boolean esPropietario;

    private Long propietarioId; // Opcional, solo si el cliente tiene un propietario asociado

    public ClienteDTO(Long clienteId, Long personaId, Boolean esPropietario, Long propietarioId) {
        this.clienteId = clienteId;
        this.personaId = personaId;
        this.esPropietario = esPropietario;
        this.propietarioId = propietarioId;
    }
}
