package com.gestVet.app.domain.dto;

import lombok.Getter;
import lombok.Setter;
import java.util.List;

@Data
@Getter
@Setter
@NoArgsConstructor
public class HistorialClinicoDTO {

    private Long historialClinicoId;

    @NotNull("La mascota es obligatoria")
    private Long mascotaId;

    public HistorialClinicoDTO(Long historialClinicoId, Long mascotaId) {
        this.historialClinicoId = historialClinicoId;
        this.mascotaId = mascotaId;
    }
}
