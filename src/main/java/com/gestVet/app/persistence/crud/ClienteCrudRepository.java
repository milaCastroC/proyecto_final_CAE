package com.gestVet.app.persistence.crud;

import com.gestVet.app.persistence.entity.Cliente;

import java.util.Optional;

import org.springframework.boot.autoconfigure.integration.IntegrationProperties.RSocket.Client;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface ClienteCrudRepository extends CrudRepository<Cliente, Long> {

    // Método para validar si un personaId ya está asociado a un cliente
    @Query(value = "SELECT * FROM cliente WHERE persona_id = ?1", nativeQuery = true)
    Optional<Client> existsByPersonaId(Long personaId);

    // Método para validar si un cliente tiene mascotas asociadas (evitar eliminación)
    @Query(value = "SELECT EXISTS(SELECT * FROM cliente JOIN mascota ON cliente.cliente_id = mascota.propietario_id WHERE cliente.cliente_id = ?1)", nativeQuery = true)
    boolean existsByClienteIdAndMascotasIsNotEmpty(Long clienteId);

}