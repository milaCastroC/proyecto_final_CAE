package com.gestionvet.gestionvet.domain.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class PropietarioDTO {
    private Long id;
    private Long clienteId;
    private List<Long> mascotaIds;
}
