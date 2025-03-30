package com.gestVet.app.domain.dto;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import jakarta.validation.constraints.NotNull;

@Data
@Getter
@Setter
@NoArgsConstructor
public class HistorialClinicoDTO {

    private Long historialClinicoId;

    @NotNull(message = "La mascota es obligatoria")
    private Long mascotaId;

    public HistorialClinicoDTO(Long historialClinicoId, Long mascotaId) {
        this.historialClinicoId = historialClinicoId;
        this.mascotaId = mascotaId;
    }
}
