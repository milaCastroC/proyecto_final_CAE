package com.gestVet.app.web.controllers;

import com.gestVet.app.domain.dto.LoginRequestDTO;
import com.gestVet.app.domain.dto.LoginResponseDTO;
import com.gestVet.app.domain.service.UsuarioService;
import com.gestVet.app.exceptions.CredencialesInvalidasException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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

    @Operation(summary = "Autenticar usuario",
            description = "Realiza la autenticación de un usuario utilizando sus credenciales (username y password)")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Login exitoso - Usuario autenticado correctamente"),
            @ApiResponse(responseCode = "400", description = "Solicitud inválida - Formato incorrecto o parámetros vacíos"),
            @ApiResponse(responseCode = "401", description = "No autorizado - Credenciales inválidas"),
            @ApiResponse(responseCode = "403", description = "Prohibido - El usuario no tiene permisos para acceder al recurso"),
            @ApiResponse(responseCode = "404", description = "No encontrado - Usuario no existe"),
            @ApiResponse(responseCode = "429", description = "Demasiadas solicitudes - Se excedió el límite de intentos"),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor - Problema al procesar la solicitud"),
            @ApiResponse(responseCode = "503", description = "Servicio no disponible - El servidor está en mantenimiento o sobrecargado")
    })
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
