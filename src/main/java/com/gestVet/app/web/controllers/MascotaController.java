package com.gestVet.app.web.controllers;

import com.gestVet.app.domain.dto.MascotaDTO;
import com.gestVet.app.domain.service.MascotaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/mascotas")
@Tag(name = "Mascotas", description = "API para gestión de mascotas")
public class MascotaController {

    private final MascotaService mascotaService;

    @Autowired
    public MascotaController(MascotaService mascotaService) {
        this.mascotaService = mascotaService;
    }

    @Operation(summary = "Obtener todas las mascotas", description = "Retorna lista completa de mascotas registradas")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Lista obtenida exitosamente"),
        @ApiResponse(responseCode = "204", description = "No hay mascotas registradas", 
                    content = @Content(schema = @Schema(hidden = true)))
    })
    @GetMapping
    public ResponseEntity<?> getAll() {
        try {
            Iterable<MascotaDTO> mascotas = mascotaService.findAll();
            if (!mascotas.iterator().hasNext()) {
                return ResponseEntity.noContent().build();
            }
            return ResponseEntity.ok(mascotas);
        } catch (Exception e) {
            throw new ResponseStatusException(
                HttpStatus.INTERNAL_SERVER_ERROR, 
                "Error al obtener mascotas: " + e.getMessage()
            );
        }
    }

    @Operation(summary = "Buscar mascota por ID", description = "Retorna una mascota específica por su ID")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Mascota encontrada"),
        @ApiResponse(responseCode = "404", description = "Mascota no encontrada"),
        @ApiResponse(responseCode = "400", description = "ID inválido")
    })
    @GetMapping("/{id}")
    public ResponseEntity<MascotaDTO> getById(
        @Parameter(description = "ID de la mascota", example = "1")
        @PathVariable Long id) {
        try {
            return mascotaService.findById(id)
                    .map(ResponseEntity::ok)
                    .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, 
                        "Mascota con ID " + id + " no encontrada"
                    ));
        } catch (IllegalArgumentException e) {
            throw new ResponseStatusException(
                HttpStatus.BAD_REQUEST, 
                "ID inválido: " + e.getMessage()
            );
        }
    }

    @Operation(summary = "Registrar nueva mascota", description = "Crea una nueva mascota en el sistema")
    @ApiResponses({
        @ApiResponse(responseCode = "201", description = "Mascota creada exitosamente"),
        @ApiResponse(responseCode = "400", description = "Datos de entrada inválidos"),
        @ApiResponse(responseCode = "404", description = "Propietario no encontrado")
    })
    @PostMapping
    public ResponseEntity<MascotaDTO> create(
        @io.swagger.v3.oas.annotations.parameters.RequestBody(
            description = "Datos de la mascota a crear",
            required = true,
            content = @Content(schema = @Schema(implementation = MascotaDTO.class))
        )
        @Validated @RequestBody MascotaDTO mascotaDTO) {
        try {
            return new ResponseEntity<>(
                mascotaService.save(mascotaDTO), 
                HttpStatus.CREATED
            );
        } catch (IllegalArgumentException e) {
            throw new ResponseStatusException(
                HttpStatus.BAD_REQUEST, 
                "Datos inválidos: " + e.getMessage()
            );
        } catch (Exception e) {
            throw new ResponseStatusException(
                HttpStatus.CONFLICT,
                "Error al crear mascota: " + e.getMessage()
            );
        }
    }

    @Operation(summary = "Actualizar mascota", description = "Actualiza los datos de una mascota existente")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Mascota actualizada"),
        @ApiResponse(responseCode = "400", description = "Datos inválidos"),
        @ApiResponse(responseCode = "404", description = "Mascota no encontrada")
    })
    @PutMapping("/{id}")
    public ResponseEntity<MascotaDTO> update(
        @Parameter(description = "ID de la mascota a actualizar", example = "1")
        @PathVariable Long id,
        @Valid @RequestBody MascotaDTO mascotaDTO) {
        try {
            mascotaDTO.setMascotaId(id);
            return ResponseEntity.ok(mascotaService.update(mascotaDTO));
        } catch (IllegalArgumentException e) {
            throw new ResponseStatusException(
                HttpStatus.NOT_FOUND,
                "Mascota no encontrada: " + e.getMessage()
            );
        }
    }

    @Operation(summary = "Eliminar mascota", description = "Elimina una mascota del sistema")
    @ApiResponses({
        @ApiResponse(responseCode = "204", description = "Mascota eliminada"),
        @ApiResponse(responseCode = "404", description = "Mascota no encontrada"),
        @ApiResponse(responseCode = "409", description = "No se puede eliminar (tiene citas asociadas)")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(
        @Parameter(description = "ID de la mascota a eliminar", example = "1")
        @PathVariable Long id) {
        try {
            mascotaService.delete(id);
            return ResponseEntity.noContent().build();
        } catch (IllegalStateException e) {
            throw new ResponseStatusException(
                HttpStatus.CONFLICT,
                e.getMessage()
            );
        } catch (IllegalArgumentException e) {
            throw new ResponseStatusException(
                HttpStatus.NOT_FOUND,
                "Mascota no encontrada"
            );
        }
    }

    @Operation(summary = "Obtener mascotas por propietario", description = "Lista mascotas de un cliente específico")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Lista obtenida exitosamente"),
        @ApiResponse(responseCode = "404", description = "Propietario no encontrado")
    })
    @GetMapping("/propietario/{propietarioId}")
    public ResponseEntity<List<MascotaDTO>> getByPropietarioId(
        @Parameter(description = "ID del propietario", example = "1")
        @PathVariable Long propietarioId) {
        try {
            return ResponseEntity.ok(mascotaService.findByPropietarioId(propietarioId));
        } catch (IllegalArgumentException e) {
            throw new ResponseStatusException(
                HttpStatus.NOT_FOUND,
                "Propietario no encontrado"
            );
        }
    }
}