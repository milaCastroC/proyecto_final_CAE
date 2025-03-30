package com.gestVet.app.domain.dto;

import lombok.Getter;
import lombok.Setter;
import java.util.List;

@Data
@Getter
@Setter
@NoArgsConstructor
public class PermisoDTO {
    private Long permisoId;

    @NotNull(message = "El nombre es obligatorio")
    private String nombre;

    public PermisoDTO(Long permisoId, String nombre) {
        this.permisoId = permisoId;
        this.nombre = nombre;
    }
}
