package com.gestVet.app.domain.dto;

@Data
@Getter
@Setter
@NoArgsConstructor
public class AdministradorDTO {
    private Long administradorId;

    @NotNull("El usuario es obligatorio")
    private Long usuarioId;

    @NotNull("El privilegio es obligatorio")
    private Long privilegioId;

    public AdministradorDTO(Long administradorId, Long usuarioId, Long privilegioId) {
        this.administradorId = administradorId;
        this.usuarioId = usuarioId;
        this.privilegioId = privilegioId;
    }
}
