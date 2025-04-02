package com.gestVet.app.domain.repository;

import com.gestVet.app.domain.dto.VeterinarioDTO;

import java.util.Optional;

public interface VeterinarioRepository {

    // Consultar todos los registros
    Iterable<VeterinarioDTO> findAll();

    // Consultar por ID
    Optional<VeterinarioDTO> findById(Long id);

    // Guardar un registro
    VeterinarioDTO save(VeterinarioDTO veterinarioDTO);

    // Actualizar un registro
    VeterinarioDTO update(VeterinarioDTO veterinarioDTO);

    // Eliminar un registro
    boolean delete(Long id);

    // Validar si existe por ID
    boolean existsById(Long id);

    // Contar todos los registros
    long count();

    // Validar si existe por ID
    boolean existsByID(Long id);

    // Validar si la tarjeta profesional es única
    boolean existsByTarjetaProfesional(String tarjetaProfesional);

}
