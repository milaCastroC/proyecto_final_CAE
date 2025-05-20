package com.gestVet.app.web.controllers;

import com.gestVet.app.domain.dto.HorarioDTO;
import com.gestVet.app.domain.service.HorarioService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@CrossOrigin(origins = "http://localhost:4200/")
@RequestMapping("/horarios")
public class HorarioController {
    @Autowired
    private HorarioService horarioService;

    // Consultar todos los horarios
    @Operation(summary = "Obtener todos los horarios", description = "Retorna una lista de todos los horarios registrados")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de horarios obtenida exitosamente"),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor")
    })
    @GetMapping("/all")
    public ResponseEntity<Iterable<HorarioDTO>> getAllHorarios() {
        Iterable<HorarioDTO> horarios = horarioService.findAll();
        return ResponseEntity.ok(horarios);
    }

    // Consultar horario por ID
    @Operation(summary = "Obtener horario por ID", description = "Retorna el horario correspondiente al ID proporcionado")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Horario encontrado"),
            @ApiResponse(responseCode = "404", description = "Horario no encontrado")
    })
    @GetMapping("/{id}")
    public ResponseEntity<HorarioDTO> getHorarioById(@PathVariable Long id) {
        Optional<HorarioDTO> horario = horarioService.findById(id);
        return horario.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Crear un nuevo horario
    @Operation(summary = "Crear horario", description = "Registra un nuevo horario en el sistema")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Horario creado exitosamente"),
            @ApiResponse(responseCode = "400", description = "Datos de horario inválidos"),
            @ApiResponse(responseCode = "409", description = "Conflicto: Horario no cumple reglas de negocio")
    })
    @PostMapping("/save")
    public ResponseEntity<HorarioDTO> createHorario(@RequestBody HorarioDTO horarioDTO) {
        if (!horarioService.validarHorasValidas(horarioDTO.getHoraInicio(), horarioDTO.getHoraFin())) {
            return ResponseEntity.badRequest().build();
        }
        HorarioDTO savedHorario = horarioService.save(horarioDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedHorario);
    }

    // Actualizar horario existente
    @Operation(summary = "Actualizar horario", description = "Actualiza los datos de un horario existente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Horario actualizado exitosamente"),
            @ApiResponse(responseCode = "400", description = "Datos de horario inválidos"),
            @ApiResponse(responseCode = "404", description = "Horario no encontrado"),
            @ApiResponse(responseCode = "409", description = "Conflicto: Horario no cumple reglas de negocio")
    })
    @PutMapping("/update/{id}")
    public ResponseEntity<HorarioDTO> updateHorario(
            @PathVariable Long id,
            @RequestBody HorarioDTO horarioDTO
    ) {
        horarioDTO.setHorarioId(id);
        if (!horarioService.validarHorasValidas(horarioDTO.getHoraInicio(), horarioDTO.getHoraFin())) {
            return ResponseEntity.badRequest().build();
        }
        HorarioDTO updatedHorario = horarioService.update(horarioDTO);
        return ResponseEntity.ok(updatedHorario);
    }

    // Eliminar horario
    @Operation(summary = "Eliminar horario", description = "Elimina el horario correspondiente al ID proporcionado")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Horario eliminado exitosamente"),
            @ApiResponse(responseCode = "404", description = "Horario no encontrado"),
            @ApiResponse(responseCode = "409", description = "Conflicto: Horario tiene citas o veterinarios asociados")
    })
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteHorario(@PathVariable Long id) {
        try {
            boolean deleted = horarioService.delete(id);
            if (deleted) {
                return ResponseEntity.ok("Horario eliminado exitosamente");
            }
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Horario no encontrado");
        } catch (IllegalStateException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        }
    }
}
