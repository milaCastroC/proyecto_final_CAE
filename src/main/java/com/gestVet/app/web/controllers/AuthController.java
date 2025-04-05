package com.gestVet.app.web.controllers;

import com.gestVet.app.domain.dto.LoginRequestDTO;
import com.gestVet.app.domain.dto.LoginResponseDTO;
import com.gestVet.app.domain.service.UsuarioService;
import com.gestVet.app.exceptions.CredencialesInvalidasException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private UsuarioService usuarioService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody @Valid LoginRequestDTO request) {
        try {
            LoginResponseDTO response = usuarioService.login(request);
            return ResponseEntity.ok(response);
        } catch (CredencialesInvalidasException e) {
            return ResponseEntity
                    .status(HttpStatus.UNAUTHORIZED)
                    .body(Map.of(
                            "error", "No autorizado",
                            "message", e.getMessage()
                    ));
        }
    }
}
