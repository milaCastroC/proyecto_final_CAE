package com.gestVet.app.persistence.repositoryImpl;

import com.gestVet.app.domain.dto.UsuarioDTO;
import com.gestVet.app.domain.repository.UsuarioRepository;
import com.gestVet.app.persistence.crud.UsuarioCrudRepository;
import com.gestVet.app.persistence.mapper.UsuarioMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class UsuarioRepositoryImpl implements UsuarioRepository {

    @Autowired
    private UsuarioCrudRepository usuarioCrudRepository;

    @Autowired
    private UsuarioMapper usuarioMapper;

    @Override
    public boolean existsById(Long id) {
        return usuarioCrudRepository.existsById(id);
    }

    @Override
    public Optional<UsuarioDTO> findById(Long id) {
        return usuarioCrudRepository.findById(id)
                .map(usuarioMapper::toDto);
    }

    @Override
    public Optional<UsuarioDTO> login(String username, String password) {
        return usuarioCrudRepository
                .login(username, password)
                .map(usuarioMapper::toDto);
    }
}
