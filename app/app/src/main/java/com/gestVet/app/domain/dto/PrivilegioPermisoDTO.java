package com.gestVet.app.domain.dto;

import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
@NoArgsConstructor
public class PrivilegioPermisoDTO {
    private Long privilegioPermisoId;

    @NotNull(message = "El privilegio es obligatorio")
    private Long privilegioId;

    @NotNull(message = "El permiso es obligatorio")
    private Long permisoId;

    public PrivilegioPermisoDTO(Long privilegioPermisoId, Long privilegioId, Long permisoId) {
        this.privilegioPermisoId = privilegioPermisoId;
        this.privilegioId = privilegioId;
        this.permisoId = permisoId;
    }
}
