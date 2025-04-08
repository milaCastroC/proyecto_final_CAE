package com.gestVet.app.web.controllers;

import com.gestVet.app.domain.dto.AdministradorDTO;
import com.gestVet.app.domain.service.AdministradorService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/administradores")
public class AdministradorController {

    @Autowired
    AdministradorService administradorService;

    // Consultar todos los registros
    @Operation(
            summary = "Obtener todos los administradores",
            description = "Retorna una lista de todos los administradores registrados"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de administradores obtenida exitosamente"),
            @ApiResponse(responseCode = "400", description = "Solicitud inválida - Parámetros incorrectos o faltantes"),
            @ApiResponse(responseCode = "401", description = "No autorizado - Se requiere autenticación"),
            @ApiResponse(responseCode = "403", description = "Prohibido - El usuario no tiene permisos para acceder a este recurso"),
            @ApiResponse(responseCode = "404", description = "No encontrado - No se encontraron administradores"),
            @ApiResponse(responseCode = "429", description = "Demasiadas solicitudes - Se excedió el límite de peticiones"),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor - Problema al procesar la solicitud"),
            @ApiResponse(responseCode = "503", description = "Servicio no disponible - El servidor está en mantenimiento o sobrecargado")
    })
    @GetMapping("/all")
    public ResponseEntity<Iterable<AdministradorDTO>> getAllAdministradores() {
        Iterable<AdministradorDTO> administradors = administradorService.findAll();
        return new ResponseEntity<>(administradors, HttpStatus.OK);
    }

    //Consultar por Id
    @Operation(
            summary = "Obtener administrador por ID",
            description = "Retorna el administrador correspondiente al ID proporcionado"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Administrador encontrado"),
            @ApiResponse(responseCode = "400", description = "Solicitud inválida - ID proporcionado en formato incorrecto"),
            @ApiResponse(responseCode = "401", description = "No autorizado - Se requiere autenticación"),
            @ApiResponse(responseCode = "403", description = "Prohibido - El usuario no tiene permisos para acceder a este recurso"),
            @ApiResponse(responseCode = "404", description = "Administrador no encontrado"),
            @ApiResponse(responseCode = "429", description = "Demasiadas solicitudes - Se excedió el límite de peticiones"),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor - Problema al procesar la solicitud"),
            @ApiResponse(responseCode = "503", description = "Servicio no disponible - El servidor está en mantenimiento o sobrecargado")
    })
    @GetMapping("/{id}")
    public ResponseEntity<AdministradorDTO> getAdministradorById(@PathVariable Long id) {
        Optional<AdministradorDTO> administrador = administradorService.findById(id);
        return administrador.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    //Crear un nuevo administrador
    @Operation(
            summary = "Crear administrador",
            description = "Crea un nuevo administrador con los datos proporcionados y retorna el registro creado"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Administrador creado exitosamente"),
            @ApiResponse(responseCode = "400", description = "Solicitud inválida - Datos proporcionados incorrectos o faltantes"),
            @ApiResponse(responseCode = "401", description = "No autorizado - Se requiere autenticación"),
            @ApiResponse(responseCode = "403", description = "Prohibido - El usuario no tiene permisos para crear administradores"),
            @ApiResponse(responseCode = "404", description = "No encontrado - Algún recurso referenciado no existe"),
            @ApiResponse(responseCode = "429", description = "Demasiadas solicitudes - Se excedió el límite de peticiones"),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor - Problema al procesar la solicitud"),
            @ApiResponse(responseCode = "503", description = "Servicio no disponible - El servidor está en mantenimiento o sobrecargado")
    })
    @PostMapping("/save")
    public ResponseEntity<?> createAdministrador(@Valid @RequestBody AdministradorDTO administradorDTO) {
        try {
            AdministradorDTO nuevoAdmin = administradorService.save(administradorDTO);
            return ResponseEntity.status(HttpStatus.CREATED).body(nuevoAdmin);
        } catch (IllegalStateException | IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleValidationErrors(MethodArgumentNotValidException ex) {
        Map<String, String> errores = new HashMap<>();
        for (FieldError error : ex.getBindingResult().getFieldErrors()) {
            errores.put(error.getField(), error.getDefaultMessage());
        }
        return ResponseEntity.badRequest().body(errores);
    }

    //Actualizar el administrador por Id
    @Operation(
            summary = "Actualizar administrador",
            description = "Actualiza la información del administrador identificado por el ID proporcionado con los datos indicados"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Administrador actualizado exitosamente"),
            @ApiResponse(responseCode = "400", description = "Solicitud inválida - Datos proporcionados incorrectos o faltantes"),
            @ApiResponse(responseCode = "401", description = "No autorizado - Se requiere autenticación"),
            @ApiResponse(responseCode = "403", description = "Prohibido - El usuario no tiene permisos para actualizar administradores"),
            @ApiResponse(responseCode = "404", description = "No encontrado - Administrador no existe"),
            @ApiResponse(responseCode = "429", description = "Demasiadas solicitudes - Se excedió el límite de peticiones"),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor - Problema al procesar la solicitud"),
            @ApiResponse(responseCode = "503", description = "Servicio no disponible - El servidor está en mantenimiento o sobrecargado")
    })
    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateAdministrador(@PathVariable Long id, @RequestBody AdministradorDTO administradorDTO) {
        try {
            AdministradorDTO adminActualizado = administradorService.update(id, administradorDTO);
            return ResponseEntity.ok(adminActualizado);
        } catch (IllegalStateException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    //Eliminar administrador por Id
    @Operation(
            summary = "Eliminar administrador",
            description = "Elimina el administrador correspondiente al ID proporcionado"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Administrador eliminado exitosamente"),
            @ApiResponse(responseCode = "400", description = "Solicitud inválida - Formato del ID incorrecto"),
            @ApiResponse(responseCode = "401", description = "No autorizado - Se requiere autenticación"),
            @ApiResponse(responseCode = "403", description = "Prohibido - El usuario no tiene permisos para eliminar administradores"),
            @ApiResponse(responseCode = "404", description = "No encontrado - Administrador no existe"),
            @ApiResponse(responseCode = "429", description = "Demasiadas solicitudes - Se excedió el límite de peticiones"),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor - Problema al procesar la solicitud"),
            @ApiResponse(responseCode = "503", description = "Servicio no disponible - El servidor está en mantenimiento o sobrecargado")
    })
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteAdministrador(@PathVariable Long id) {
        boolean deleted = administradorService.delete(id);
        if (deleted) {
            return ResponseEntity.ok("Administrador eliminado exitosamente");
        }else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Error: Administrador no encontrado");
        }
    }

}
