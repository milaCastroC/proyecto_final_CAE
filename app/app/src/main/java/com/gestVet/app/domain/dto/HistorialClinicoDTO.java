package com.gestionvet.gestionvet.domain.dto;

import lombok.Getter;
import lombok.Setter;
import java.util.List;

@Getter
@Setter
public class HistorialClinicoDTO {
    private Long id;
    private Long mascotaId;
    private List<Long> itemHistorialIds; // Lista de IDs de los ítems del historial
}
