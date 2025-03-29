package com.gestionvet.gestionvet.domain.dto;

import lombok.Getter;
import lombok.Setter;
import java.util.List;

@Getter
@Setter
public class PermisoDTO {
    private Long id;
    private String nombre;
    private List<Long> privilegioPermisoIds;
}
