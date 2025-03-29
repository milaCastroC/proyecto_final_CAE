package com.gestionvet.gestionvet.domain.dto;

import lombok.Getter;
import lombok.Setter;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
public class MascotaDTO {
    private Long id;
    private Long propietarioId;
    private String nombre;
    private String especie;
    private String raza;
    private String sexo;
    private LocalDate fechaNacimiento;
    private Integer edad;
    private BigDecimal peso;
    private List<Long> citaIds;
    private List<Long> historialClinicoIds;
}


