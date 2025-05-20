package com.gestVet.app.web.controllers;


import com.gestVet.app.domain.dto.ItemHistorialDTO;
import com.gestVet.app.domain.service.ItemHistorialService;
import com.gestVet.app.exceptions.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.time.LocalDate;
import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200/")
@RequestMapping("/api/historial")
@RequiredArgsConstructor
@Tag(name = "ItemHistorial Controller", description = "API para gestionar los registros de historial clínico de mascotas")
public class ItemHistorialController {

    private final ItemHistorialService service;

    @Operation(summary = "Crear nuevo registro de historial", description = "Crea un nuevo registro en el historial clínico de una mascota")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Registro creado exitosamente"),
            @ApiResponse(responseCode = "400", description = "Datos de entrada inválidos"),
            @ApiResponse(responseCode = "404", description = "Mascota no encontrada")
    })
    @PostMapping("/create")
    public ResponseEntity<ItemHistorialDTO> createItemHistorial(@Valid @RequestBody ItemHistorialDTO itemHistorialDTO) {
        try {
            ItemHistorialDTO createdItem = service.create(itemHistorialDTO);
            return ResponseEntity.status(HttpStatus.CREATED).body(createdItem);
        } catch (MascotaNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @Operation(summary = "Crear registro de historial a partir de una cita", description = "Crea un nuevo registro de historial vinculado a una cita existente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Registro creado exitosamente"),
            @ApiResponse(responseCode = "400", description = "Datos inválidos o cita no atendida"),
            @ApiResponse(responseCode = "404", description = "Cita o mascota no encontrada")
    })
    @PostMapping("/create/from-cita/{citaId}")
    public ResponseEntity<ItemHistorialDTO> createFromCita(@PathVariable Long citaId, @Valid @RequestBody ItemHistorialDTO itemHistorialDTO) {
        try {
            ItemHistorialDTO createdItem = service.createFromCita(citaId, itemHistorialDTO);
            return ResponseEntity.status(HttpStatus.CREATED).body(createdItem);
        } catch (CitaNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } catch (CitaNotAttendedException | MascotaCitaMismatchException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @Operation(summary = "Obtener registro de historial por ID", description = "Busca un registro específico del historial clínico por su ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Registro encontrado exitosamente"),
            @ApiResponse(responseCode = "404", description = "Registro no encontrado")
    })
    @GetMapping("/{id}")
    public ResponseEntity<ItemHistorialDTO> getItemHistorialById(@PathVariable Long id) {
        try {
            ItemHistorialDTO item = service.getById(id);
            return ResponseEntity.ok(item);
        } catch (ItemHistorialNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @Operation(summary = "Listar todos los registros", description = "Obtiene todos los registros de historial clínico")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Registros listados exitosamente")
    })
    @GetMapping("/all")
    public ResponseEntity<List<ItemHistorialDTO>> getAllItemsHistorial() {
        List<ItemHistorialDTO> items = service.findAll();
        return ResponseEntity.ok(items);
    }

    @Operation(summary = "Listar historial de una mascota", description = "Obtiene todos los registros de historial clínico de una mascota específica")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Historial listado exitosamente"),
            @ApiResponse(responseCode = "404", description = "Mascota no encontrada")
    })
    @GetMapping("/by-mascota/{mascotaId}")
    public ResponseEntity<List<ItemHistorialDTO>> getHistorialByMascota(@PathVariable Long mascotaId) {
        try {
            List<ItemHistorialDTO> items = service.findByMascotaId(mascotaId);
            return ResponseEntity.ok(items);
        } catch (MascotaNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @Operation(summary = "Buscar por tipo de registro", description = "Filtra registros de historial clínico por su tipo")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Registros filtrados exitosamente")
    })
    @GetMapping("/by-tipo/{tipo}")
    public ResponseEntity<List<ItemHistorialDTO>> getHistorialByTipo(@PathVariable String tipo) {
        List<ItemHistorialDTO> items = service.findByTipo(tipo);
        return ResponseEntity.ok(items);
    }

    @Operation(summary = "Buscar por rango de fechas", description = "Filtra registros de historial clínico entre dos fechas específicas")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Registros filtrados exitosamente"),
            @ApiResponse(responseCode = "400", description = "Formato de fecha inválido")
    })
    @GetMapping("/by-fecha")
    public ResponseEntity<List<ItemHistorialDTO>> getHistorialByFechas(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate inicio,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fin) {
        List<ItemHistorialDTO> items = service.findByFechaBetween(inicio, fin);
        return ResponseEntity.ok(items);
    }

    @Operation(summary = "Contar registros por mascota", description = "Obtiene el número total de registros de historial para una mascota")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Conteo realizado exitosamente"),
            @ApiResponse(responseCode = "404", description = "Mascota no encontrada")
    })
    @GetMapping("/count/{mascotaId}")
    public ResponseEntity<Long> countHistorialByMascota(@PathVariable Long mascotaId) {
        try {
            long count = service.countByMascotaId(mascotaId);
            return ResponseEntity.ok(count);
        } catch (MascotaNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @Operation(summary = "Actualizar registro de historial", description = "Actualiza los datos de un registro de historial existente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Registro actualizado exitosamente"),
            @ApiResponse(responseCode = "400", description = "Datos de entrada inválidos"),
            @ApiResponse(responseCode = "404", description = "Registro no encontrado")
    })
    @PutMapping("/update/{id}")
    public ResponseEntity<ItemHistorialDTO> updateItemHistorial(@PathVariable Long id, @Valid @RequestBody ItemHistorialDTO itemHistorialDTO) {
        try {
            ItemHistorialDTO updatedItem = service.update(id, itemHistorialDTO);
            return ResponseEntity.ok(updatedItem);
        } catch (ItemHistorialNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @Operation(summary = "Eliminar registro de historial", description = "Elimina el registro de historial correspondiente al ID proporcionado")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Registro eliminado exitosamente"),
            @ApiResponse(responseCode = "404", description = "Registro no encontrado")
    })
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteItemHistorial(@PathVariable Long id) {
        try {
            service.deleteById(id);
            return ResponseEntity.ok("Registro de historial eliminado exitosamente");
        } catch (ItemHistorialNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Error: Registro de historial no encontrado");
        }
    }
}