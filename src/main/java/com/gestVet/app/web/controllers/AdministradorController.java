package com.gestVet.app.web.controllers;

import com.gestVet.app.domain.dto.AdministradorDTO;
import com.gestVet.app.domain.service.AdministradorService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/administradores")
public class AdministradorController {

    @Autowired
    AdministradorService administradorService;

    // Consultar todos los registros
    @Operation(summary = "Obtener todos los administradores", description = "Retorna una lista de todos los administradores registrados")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de administradores obtenida exitosamente"),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor")
    })
    @GetMapping("/all")
    public ResponseEntity<Iterable<AdministradorDTO>> getAllAdministradores() {
        Iterable<AdministradorDTO> administradors = administradorService.findAll();
        return new ResponseEntity<>(administradors, HttpStatus.OK);
    }

    //Consultar por Id
    @Operation(summary = "Obtener administrador por ID", description = "Retorna el administrador correspondiente al ID proporcionado")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Administrador encontrado"),
            @ApiResponse(responseCode = "404", description = "Administrador no encontrado")
    })
    @GetMapping("/{id}")
    public ResponseEntity<AdministradorDTO> getAdministradorById(@PathVariable Long id) {
        Optional<AdministradorDTO> administrador = administradorService.findById(id);
        return administrador.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    //Crear un nuevo administrador
    @Operation(summary = "Guardar nuevo administrador", description = "Guarda un nuevo administrador en el sistema")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Administrador creado exitosamente"),
            @ApiResponse(responseCode = "400", description = "Error al crear el administrador")
    })
    @PostMapping("/save")
    public ResponseEntity<?> createAdministrador(@RequestBody AdministradorDTO administradorDTO) {
        try {
            AdministradorDTO nuevoAdmin = administradorService.save(administradorDTO);
            return ResponseEntity.status(HttpStatus.CREATED).body(nuevoAdmin);
        } catch (IllegalStateException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    //Actualizar el administrador por Id
    @Operation(summary = "Actualizar administrador", description = "Actualiza los datos de un administrador existente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Administrador actualizado exitosamente"),
            @ApiResponse(responseCode = "400", description = "Error al actualizar el administrador"),
            @ApiResponse(responseCode = "404", description = "Administrador no encontrado")
    })
    @PutMapping
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
    @Operation(summary = "Eliminar administrador", description = "Elimina el administrador correspondiente al ID proporcionado")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Administrador eliminado exitosamente"),
            @ApiResponse(responseCode = "404", description = "Administrador no encontrado")
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
