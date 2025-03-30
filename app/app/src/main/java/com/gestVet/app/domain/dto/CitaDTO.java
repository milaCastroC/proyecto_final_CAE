package com.gestVet.app.domain.dto;

import lombok.Getter;
import lombok.Setter;
import java.time.LocalDateTime;

@Data
@Getter
@Setter
@NoArgsConstructor
public class CitaDTO {
    private Long citaId;

    @NotNull("La mascota es obligatoria")
    private Long mascotaId;

    @NotNull("El horario es obligatorio")
    private Long horarioId;

    @NotNull("El veterinario es obligatorio")
    private Long veterinarioId;

    @NotNull("La fecha es obligatoria")
    @Future(message = "La fecha debe ser en el futuro")
    private LocalDateTime fecha;

    @NotNull("El tipo de cita es obligatoria")
    private Long tipoCitaId;

    @NotBlank("El estado es obligatorio")
    private String estado;

    public CitaDTO(Long citaId, Long mascotaId, Long horarioId, Long veterinarioId, LocalDateTime fecha, Long tipoCitaId, String estado) {
        this.citaId = citaId;
        this.mascotaId = mascotaId;
        this.horarioId = horarioId;
        this.veterinarioId = veterinarioId;
        this.fecha = fecha;
        this.tipoCitaId = tipoCitaId;
        this.estado = estado;
    }
}
