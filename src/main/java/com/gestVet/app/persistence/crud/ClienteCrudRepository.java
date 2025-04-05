package com.gestVet.app.persistence.crud;

import com.gestVet.app.persistence.entity.Cliente;

import java.util.Optional;

import org.springframework.boot.autoconfigure.integration.IntegrationProperties.RSocket.Client;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface ClienteCrudRepository extends CrudRepository<Cliente, Long> {

    // Método para validar si un personaId ya está asociado a un cliente
    @Query("SELECT c FROM Cliente c WHERE c.personaId = ?1")
    Optional<Cliente> findByPersonaId(Long personaId);

    @Query("SELECT CASE WHEN COUNT(m) > 0 THEN true ELSE false END FROM Mascota m WHERE m.propietario.personaId = ?1")
    boolean existsMascotasByClienteId(Long personaId);

}