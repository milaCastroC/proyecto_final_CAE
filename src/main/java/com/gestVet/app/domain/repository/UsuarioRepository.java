package com.gestVet.app.domain.repository;

import com.gestVet.app.domain.dto.UsuarioDTO;

import java.util.Optional;

public interface UsuarioRepository {

    boolean existsById(Long id);

    Optional<UsuarioDTO> findById(Long id);

    Optional<UsuarioDTO> login(String username, String password);

    boolean existsByIdentificacionAndTipoIdentificacion(String identificacion, String tipoIdentificacion);
}
