package com.gestVet.app.domain.repository;

import com.gestVet.app.domain.dto.HorarioDTO;

import java.util.Optional;

public interface HorarioRepository {

    // Consultar todos los registros
    Iterable<HorarioDTO> findAll();

    // Consultar por ID
    Optional<HorarioDTO> findById(Long id);

    // Guardar un registro
    HorarioDTO save(HorarioDTO veterinarioDTO);

    // Actualizar un registro
    HorarioDTO update(HorarioDTO veterinarioDTO);

    // Eliminar un registro
    boolean delete(Long id);

    // Validar si existe por ID
    boolean existsById(Long id);

}
