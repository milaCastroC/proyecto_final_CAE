package com.gestVet.app.domain.service;

import com.gestVet.app.domain.dto.LoginRequestDTO;
import com.gestVet.app.domain.dto.LoginResponseDTO;
import com.gestVet.app.domain.dto.UsuarioDTO;
import com.gestVet.app.domain.repository.UsuarioRepository;
import com.gestVet.app.exceptions.CredencialesInvalidasException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    public LoginResponseDTO login(LoginRequestDTO request) {
        Optional<UsuarioDTO> usuarioOpt = usuarioRepository.login(request.getUsername(), request.getPassword());

        if (usuarioOpt.isEmpty()) {
            throw new CredencialesInvalidasException();
        }

        UsuarioDTO usuario = usuarioOpt.get();
        String nombreCompleto = usuario.getNombre() + " " + usuario.getApellido();

        return new LoginResponseDTO(
                usuario.getPersonaId(),
                nombreCompleto,
                usuario.getRol()
        );
    }
}
