package com.gestVet.app.web.controllers;

import com.gestVet.app.domain.dto.CitaDTO;
import com.gestVet.app.domain.service.CitaService;
import com.gestVet.app.exceptions.CitaModificacionNoPermitidaException;
import com.gestVet.app.exceptions.CitaNotFoundException;
import com.gestVet.app.exceptions.MascotaNotFoundException;
import com.gestVet.app.exceptions.VeterinarioNotFoundException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/citas")
public class CitaController {

    @Autowired
    private CitaService citaService;

    @Operation(summary = "Obtener todas las citas", description = "Retorna una lista de todas las citas registradas")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de citas obtenida exitosamente"),
            @ApiResponse(responseCode = "204", description = "No hay citas registradas"),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor")
    })
    @GetMapping("/all")
    public ResponseEntity<Iterable<CitaDTO>> getAllCitas() {
        Iterable<CitaDTO> citas = citaService.findAll();
        return citas.iterator().hasNext()
                ? ResponseEntity.ok(citas)
                : ResponseEntity.noContent().build();
    }

    @Operation(summary = "Obtener cita por ID", description = "Retorna la cita correspondiente al ID proporcionado")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Cita encontrada con éxito"),
            @ApiResponse(responseCode = "400", description = "Solicitud inválida - Datos proporcionados incorrectos"),
            @ApiResponse(responseCode = "404", description = "Cita no encontrada"),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor")
    })
    @GetMapping("/{id}")
    public ResponseEntity<CitaDTO> getCitaById(
            @Parameter(description = "ID de la cita", required = true)
            @PathVariable Long id) {
        Optional<CitaDTO> cita = citaService.findById(id);
        return cita.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Operation(summary = "Registrar nueva cita", description = "Crea una nueva cita en el sistema")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Cita creada exitosamente"),
            @ApiResponse(responseCode = "400", description = "Solicitud inválida - Datos proporcionados incorrectos"),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor")
    })
    @PostMapping("/save")
    public ResponseEntity<CitaDTO> createCita(
            @Parameter(description = "Datos de la cita", required = true)
            @RequestBody CitaDTO citaDTO) {
        try {
            CitaDTO nuevaCita = citaService.save(citaDTO);
            return new ResponseEntity<>(nuevaCita, HttpStatus.CREATED);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @Operation(summary = "Actualizar estado de cita", description = "Actualiza el estado de una cita existente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Estado de cita actualizado exitosamente"),
            @ApiResponse(responseCode = "400", description = "Modificación no permitida"),
            @ApiResponse(responseCode = "404", description = "Cita no encontrada"),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor")
    })
    @PutMapping("/update/{id}")
    public ResponseEntity<CitaDTO> updateEstado(
            @Parameter(description = "ID de la cita", required = true)
            @PathVariable Long id,
            @Parameter(description = "Datos actualizados de la cita", required = true)
            @RequestBody CitaDTO citaDTO) {
        try {
            citaDTO.setCitaId(id);
            CitaDTO updatedCita = citaService.updateEstado(citaDTO);
            return ResponseEntity.ok(updatedCita);
        } catch (CitaNotFoundException e) {
            return ResponseEntity.notFound().build();
        } catch (CitaModificacionNoPermitidaException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @Operation(summary = "Cancelar cita", description = "Cancela una cita existente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Cita cancelada exitosamente"),
            @ApiResponse(responseCode = "400", description = "Solicitud inválida - Datos proporcionados incorrectos"),
            @ApiResponse(responseCode = "404", description = "Cita no encontrada"),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor")
    })
    @PutMapping("/cancel/{id}")
    public ResponseEntity<String> cancelarCita(
            @Parameter(description = "ID de la cita", required = true)
            @PathVariable Long id) {
        try {
            boolean canceled = citaService.cancelarCita(id);
            return ResponseEntity.ok("Cita cancelada exitosamente");
        } catch (CitaNotFoundException e) {
            e.printStackTrace();
            return ResponseEntity.notFound().build();
        }
    }

    // Consultar citas por veterinario
    @Operation(summary = "Filtrar citas por veterinario", description = "Retorna citas asociadas a un veterinario")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Citas encontradas"),
            @ApiResponse(responseCode = "204", description = "No hay citas para el veterinario"),
            @ApiResponse(responseCode = "400", description = "Solicitud inválida - Datos proporcionados incorrectos"),
            @ApiResponse(responseCode = "404", description = "Veterinario no encontrado"),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor")
    })
    @GetMapping("/veterinario/{veterinarioId}")
    public ResponseEntity<?> getByVeterinario(
            @Parameter(description = "ID válido del veterinario", required = true)
            @PathVariable Long veterinarioId) {
        try{
            Iterable<CitaDTO> citas = citaService.findByVeterinarioId(veterinarioId);
            return citas.iterator().hasNext()
                    ? ResponseEntity.ok(citas)
                    : ResponseEntity.noContent().build();
        } catch (VeterinarioNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    // Consultar citas por mascota
    @Operation(summary = "Filtrar citas por mascota", description = "Retorna citas asociadas a una mascota")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Citas encontradas"),
            @ApiResponse(responseCode = "204", description = "No hay citas para la mascota"),
            @ApiResponse(responseCode = "400", description = "Solicitud inválida - Datos proporcionados incorrectos"),
            @ApiResponse(responseCode = "404", description = "Mascota no encontrada"),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor")
    })
    @GetMapping("/mascota/{mascotaId}")
    public ResponseEntity<?> getByMascota(
            @Parameter(description = "ID válido de la mascota", required = true)
            @PathVariable Long mascotaId) {
        try{
            Iterable<CitaDTO> citas = citaService.findByMascotaId(mascotaId);
            return citas.iterator().hasNext()
                    ? ResponseEntity.ok(citas)
                    : ResponseEntity.noContent().build();
        } catch(MascotaNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    // Consultar citas por fecha
    @Operation(summary = "Filtrar citas por fecha", description = "Retorna citas en una fecha específica")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Citas encontradas"),
            @ApiResponse(responseCode = "204", description = "No hay citas en esta fecha"),
            @ApiResponse(responseCode = "400", description = "Formato de fecha inválido"),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor")
    })
    @GetMapping("/fecha")
    public ResponseEntity<?> getByFecha(
            @Parameter(description = "Fecha en formato ISO (yyyy-MM-dd)", example = "2025-12-31")
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDate fecha) {
        Iterable<CitaDTO> citas = citaService.findByFecha(fecha);
        return citas.iterator().hasNext()
                ? ResponseEntity.ok(citas)
                : ResponseEntity.noContent().build();
    }

    // Consultar citas por estado
    @Operation(summary = "Filtrar citas por estado", description = "Retorna citas según su estado (PENDIENTE/COMPLETADA/CANCELADA)")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Citas encontradas"),
            @ApiResponse(responseCode = "204", description = "No hay citas con este estado"),
            @ApiResponse(responseCode = "400", description = "Estado de la cita no válido"),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor")
    })
    @GetMapping("/estado/{estado}")
    public ResponseEntity<?> getByEstado(
            @Parameter(description = "Estado de la cita", required = true)
            @PathVariable String estado) {
        if (!List.of("Atendida", "Confirmada", "Cancelada").contains(estado)) {
            return ResponseEntity.badRequest().body("Error: Estado no válido. Valores permitidos: Atendida, Confirmada, Cancelada");
        }
        Iterable<CitaDTO> citas = citaService.findByEstado(estado);
        return citas.iterator().hasNext()
                ? ResponseEntity.ok(citas)
                : ResponseEntity.noContent().build();
    }

//    // Consultar citas por veterinario, fecha y estado
//    @Operation(summary = "Filtrar citas combinado", description = "Retorna citas por veterinario, fecha y estado")
//    @ApiResponses(value = {
//            @ApiResponse(responseCode = "200", description = "Citas encontradas"),
//            @ApiResponse(responseCode = "204", description = "No hay citas con estos criterios"),
//            @ApiResponse(responseCode = "400", description = "Parámetros inválidos"),
//            @ApiResponse(responseCode = "404", description = "Veterinario no encontrado"),
//            @ApiResponse(responseCode = "500", description = "Error interno del servidor")
//    })
//    @GetMapping("/combinado")
//    public ResponseEntity<?> getByVeterinarioFechaEstado(
//            @Parameter(description = "ID válido del veterinario", required = true)
//            @RequestParam Long veterinarioId,
//            @Parameter(description = "Fecha en formato ISO (yyyy-MM-dd)", example = "2023-12-31")
//            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fecha,
//            @Parameter(description = "Estado de la cita", required = true)
//            @RequestParam String estado) {
//
//            if (!List.of("Atendida", "Confirmada", "Cancelada").contains(estado)) {
//                return ResponseEntity.badRequest().body("Estado no válido");
//            }
//            Iterable<CitaDTO> citas = citaService.findByVeterinarioIdAndFechaAndEstado(veterinarioId, fecha, estado);
//            return citas.iterator().hasNext()
//                    ? ResponseEntity.ok(citas)
//                    : ResponseEntity.noContent().build();
//    }

}
