package com.gestVet.app.domain.repository;

import com.gestVet.app.domain.dto.CitaDTO;

import java.time.LocalDateTime;
import java.util.Optional;

public interface CitaRepository {

    //Consultar todas las citas
    Iterable<CitaDTO> findAll();

    // Consultar cita por ID
    Optional<CitaDTO> findById(Long id);

    // Guardar nueva cita
    CitaDTO save(CitaDTO citaDTO);

    // Actualizar estado
    CitaDTO updateEstado(CitaDTO citaDTO);

    // Verificar si la cita existe
    boolean existsById(Long id);


    // Consultar Citas por veterinario
    Iterable<CitaDTO> findByVeterinarioId(Long id);

    // Consultar Citas por mascota
    Iterable<CitaDTO> findByMascotaId(Long id);

    // Consultar Citas por fecha
    Iterable<CitaDTO> findByFecha(LocalDateTime fecha);

    // Consultar Citas por estado
    Iterable<CitaDTO> findByEstado(String estado);

    // Consultar Cita por Veterinario, fecha y estado
    Iterable<CitaDTO> findByVeterinarioIdAndFechaAndEstado(Long vetId, LocalDateTime fecha, String estado);

    // TODO Validar horarios del veterinario
}
