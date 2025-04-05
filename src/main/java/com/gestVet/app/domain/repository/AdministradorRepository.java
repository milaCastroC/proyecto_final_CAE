package com.gestVet.app.domain.repository;

import com.gestVet.app.domain.dto.AdministradorDTO;

import java.util.Optional;

public interface AdministradorRepository {

    //Guardar un nuevo administrador
    AdministradorDTO save(AdministradorDTO adminDTO);

    //Editar un administrador
    AdministradorDTO update(AdministradorDTO adminDTO);

    //Consultar todos los administradores
    Iterable<AdministradorDTO> findAll();

    //Buscar un administrador por Id
    Optional<AdministradorDTO> findById(Long id);

    //Eliminar un administrador
    boolean delete(Long id);

    //Validar si existe un administrador
    boolean existsById(Long id);

    //Validar si existe por cargo y area
    boolean existsByCargoAndArea(String cargo, String area);

    boolean existsByUsuarioId(Long usuarioId);

    Optional<AdministradorDTO> findByUsuarioId(Long usuarioId);
}
