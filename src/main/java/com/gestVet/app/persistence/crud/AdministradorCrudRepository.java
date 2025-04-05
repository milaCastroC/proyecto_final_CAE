package com.gestVet.app.persistence.crud;

import com.gestVet.app.persistence.entity.Administrador;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface AdministradorCrudRepository extends CrudRepository<Administrador, Long> {
    //Validar si existe por cargo y area
    boolean existsByCargoAndArea(String cargo, String area);

    boolean existsByPersonaId(Long personaId);

    Optional<Administrador> findByPersonaId(Long personaId);
}
