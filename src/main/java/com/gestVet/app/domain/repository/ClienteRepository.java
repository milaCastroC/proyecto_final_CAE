package com.gestVet.app.domain.repository;

import com.gestVet.app.domain.dto.ClienteDTO;
import java.util.Optional;

public interface ClienteRepository {

    // Consultar todos los clientes
    Iterable<ClienteDTO> findAll();

    // Consultar cliente por ID
    Optional<ClienteDTO> findById(Long id);

    // Guardar nuevo cliente
    ClienteDTO save(ClienteDTO clienteDTO);

    // Actualizar cliente existente
    ClienteDTO update(ClienteDTO clienteDTO);

    // Eliminar cliente por ID
    boolean delete(Long id);

    // Verificar si cliente existe por ID
    boolean existsById(Long id);

    // Contar total de clientes
    long count();

    // Verificar si personaId ya está registrado
    boolean existsByPersonaId(Long personaId);

    // Verificar si cliente tiene mascotas asociadas
    boolean existsByClienteIdAndMascotasIsNotEmpty(Long clienteId);
}