package com.gestVet.app.domain.dto;

import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
@NoArgsConstructor
public class VeterinarioDTO {
    private Long veterinarioId;

    @NotNull(message = "La especialidad es obligatoria")
    private String especialidad;

    @NotNull(message = "La tarjeta profesional es obligatoria")
    private String tarjetaProfesional;

    @NotNull(message = "El usuario es obligatorio")
    private Long usuarioId;

    public VeterinarioDTO(Long veterinarioId, String especialidad, String tarjetaProfesional, Long usuarioId) {
        this.veterinarioId = veterinarioId;
        this.especialidad = especialidad;
        this.tarjetaProfesional = tarjetaProfesional;
        this.usuarioId = usuarioId;
    }
}
