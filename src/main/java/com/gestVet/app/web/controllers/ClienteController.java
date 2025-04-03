package com.gestVet.app.web.controllers;

import com.gestVet.app.domain.dto.ClienteDTO;
import com.gestVet.app.domain.service.ClienteService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/clientes")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    @Operation(summary = "Obtener todos los clientes", description = "Retorna una lista de todos los clientes registrados")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de clientes obtenida exitosamente"),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor")
    })
    @GetMapping("/all")
    public ResponseEntity<Iterable<ClienteDTO>> getAllClientes() {
        Iterable<ClienteDTO> clientes = clienteService.findAll();
        return new ResponseEntity<>(clientes, HttpStatus.OK);
    }

    @Operation(summary = "Obtener cliente por ID", description = "Retorna el cliente correspondiente al ID proporcionado")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Cliente encontrado"),
            @ApiResponse(responseCode = "404", description = "Cliente no encontrado")
    })
    @GetMapping("/{id}")
    public ResponseEntity<ClienteDTO> getClienteById(@PathVariable Long id) {
        Optional<ClienteDTO> cliente = clienteService.findById(id);
        return cliente.map(ResponseEntity::ok)
                    .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Operation(summary = "Registrar nuevo cliente", description = "Crea un nuevo cliente en el sistema")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Cliente creado exitosamente"),
            @ApiResponse(responseCode = "400", description = "Error al crear el cliente")
    })
    @PostMapping("/save")
    public ResponseEntity<ClienteDTO> createCliente(@RequestBody ClienteDTO clienteDTO) {
        ClienteDTO savedCliente = clienteService.save(clienteDTO);
        return new ResponseEntity<>(savedCliente, HttpStatus.CREATED);
    }

    @Operation(summary = "Actualizar cliente", description = "Actualiza los datos de un cliente existente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Cliente actualizado exitosamente"),
            @ApiResponse(responseCode = "400", description = "Error al actualizar el cliente"),
            @ApiResponse(responseCode = "404", description = "Cliente no encontrado")
    })
    @PutMapping("/update/{id}")
    public ResponseEntity<ClienteDTO> updateCliente(@PathVariable Long id, @RequestBody ClienteDTO clienteDTO) {
        clienteDTO.setClienteId(id);
        ClienteDTO updatedCliente = clienteService.update(clienteDTO);
        return ResponseEntity.ok(updatedCliente);
    }

    @Operation(summary = "Eliminar cliente", description = "Elimina el cliente correspondiente al ID proporcionado")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Cliente eliminado exitosamente"),
            @ApiResponse(responseCode = "404", description = "Cliente no encontrado")
    })
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteCliente(@PathVariable Long id) {
        boolean deleted = clienteService.delete(id);
        if (deleted) {
            return ResponseEntity.ok("Cliente eliminado exitosamente");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Error: Cliente no encontrado");
        }
    }

    @Operation(summary = "Contar clientes", description = "Retorna el número total de clientes registrados")
    @ApiResponse(responseCode = "200", description = "Número total de clientes")
    @GetMapping("/count")
    public ResponseEntity<Long> countClientes() {
        long count = clienteService.count();
        return new ResponseEntity<>(count, HttpStatus.OK);
    }
}