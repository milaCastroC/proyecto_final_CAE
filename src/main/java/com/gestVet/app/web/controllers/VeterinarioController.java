package com.gestVet.app.web.controllers;

import com.gestVet.app.domain.dto.VeterinarioDTO;
import com.gestVet.app.domain.service.VeterinarioService;
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
@RequestMapping("/veterinarios")
public class VeterinarioController {

    @Autowired
    VeterinarioService veterinarioService;

    // Consultar todos los registros
    @Operation(summary = "Obtener todos los veterinarios", description = "Retorna una lista de todos los Veterinarios registrados")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de veterinarios obtenida exitosamente"),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor")
    })
    @GetMapping("/all")
    public ResponseEntity<Iterable<VeterinarioDTO>> getAllVeterinarios() {
        Iterable<VeterinarioDTO> veterinarios = veterinarioService.findAll();
        return new ResponseEntity<>(veterinarios, HttpStatus.OK);
    }

    // Consultar por ID
    @Operation(summary = "Obtener veterinarioistrador por ID", description = "Retorna el veterinario correspondiente al ID proporcionado")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Veterinarioistrador encontrada"),
            @ApiResponse(responseCode = "404", description = "Veterinarioistrador no encontrada")
    })
    @GetMapping("/{id}")
    public ResponseEntity<VeterinarioDTO> getVeterinarioById(@PathVariable Long id) {
        Optional<VeterinarioDTO> veterinario = veterinarioService.findById(id);
        return veterinario.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Crear un nuevo veterinario
    @Operation(summary = "Guardar nuevo veterinario", description = "Guarda un nuevo veterinario en el sistema")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Veterinario creado exitosamente"),
            @ApiResponse(responseCode = "400", description = "Error al crear el veterinario")
    })
    @PostMapping("/save")
    public ResponseEntity<VeterinarioDTO> createVeterinario(@RequestBody VeterinarioDTO veterinarioDTO) {
        VeterinarioDTO savedVet = veterinarioService.save(veterinarioDTO);
        return new ResponseEntity<>(savedVet, HttpStatus.CREATED);
    }

    //Actualizar veterinario por Id
    @Operation(summary = "Actualizar veterinario", description = "Actualiza los datos de un veterinario existente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Veterinario actualizado exitosamente"),
            @ApiResponse(responseCode = "400", description = "Error al actualizar el Veterinario"),
            @ApiResponse(responseCode = "404", description = "Veterinario no encontrado")
    })
    @PutMapping("/update/{id}")
    public ResponseEntity<VeterinarioDTO> updateVeterinario(@PathVariable Long id, @RequestBody VeterinarioDTO veterinarioDTO) {
        veterinarioDTO.setPersonaId(id);
        VeterinarioDTO updatedVeterinario = veterinarioService.update(veterinarioDTO);
        return ResponseEntity.ok(updatedVeterinario);
    }

    // Eliminar Veterinario por ID
    @Operation(summary = "Eliminar veterinario", description = "Elimina el veterinario correspondiente al ID proporcionado")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Veterinario eliminado exitosamente"),
            @ApiResponse(responseCode = "404", description = "Veterinario no encontrado")
    })
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteVeterinario(@PathVariable Long id) {
        boolean deleted = veterinarioService.delete(id);
        if (deleted) {
            return ResponseEntity.ok("Veterinario eliminado exitosamente");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Error: Veterinario no encontrado");
        }
    }

    // Contar el número total de Veterinarios
    @Operation(summary = "Contar veterinarios", description = "Retorna el número total de Veterinarios registrados")
    @ApiResponse(responseCode = "200", description = "Número total de veterinarios")
    @GetMapping("/count")
    public ResponseEntity<Long> countVeterinarios() {
        long count = veterinarioService.count();
        return new ResponseEntity<>(count, HttpStatus.OK);
    }

}
