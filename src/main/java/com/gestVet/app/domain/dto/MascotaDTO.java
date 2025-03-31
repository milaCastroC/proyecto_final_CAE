package com.gestVet.app.domain.dto;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;

@Data
@Getter
@Setter
@NoArgsConstructor
public class MascotaDTO {
    private Long mascotaId;

    @NotNull(message = "El propietario es obligatorio")
    private Long propietarioId;

    @NotNull(message = "El nombre es obligatorio")
    private String nombre;

    @NotNull(message = "La especie es obligatoria")
    private String especie;

    private String raza;

    @NotNull(message = "El sexo es obligatorio")
    private String sexo;

    @Past(message = "La fecha de nacimiento debe ser en el pasado")
    private LocalDate fechaNacimiento;

    private Integer edad;

    private BigDecimal peso;

    public MascotaDTO(Long mascotaId, Long propietarioId, String nombre, String especie, String raza, String sexo, LocalDate fechaNacimiento, Integer edad, BigDecimal peso) {
        this.mascotaId = mascotaId;
        this.propietarioId = propietarioId;
        this.nombre = nombre;
        this.especie = especie;
        this.raza = raza;
        this.sexo = sexo;
        this.fechaNacimiento = fechaNacimiento;
        this.edad = edad;
        this.peso = peso;
    }
}


