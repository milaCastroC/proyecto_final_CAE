package com.gestVet.app.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ItemHistorialDTO {
    private Long itemHistorialId;

    @NotNull(message = "El historial id de mascota es obligatorio")
    private Long mascotaId;

    @NotNull(message = "La fecha es obligatoria")
    @PastOrPresent(message = "La fecha no puede ser futura")
    private LocalDate fecha;

    @NotNull(message = "El diagnóstico es obligatorio")
    @NotBlank(message = "El diagnóstico no puede estar vacío")
    private String diagnostico;

    @NotNull(message = "El tratamiento es obligatorio")
    @NotBlank(message = "El tratamiento no puede estar vacío")
    private String tratamiento;

    private String observaciones;

    private String tipo;
    
    // Añadidos para soportar la creación desde cita
    private Long citaId;
    private String tipoCita;
}