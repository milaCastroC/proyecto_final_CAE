package com.gestVet.app.persistence.crud;

import com.gestVet.app.persistence.entity.Veterinario;
import org.springframework.data.repository.CrudRepository;

public interface VeterinarioCrudRepository extends CrudRepository<Veterinario, Long> {

    boolean existsByTarjetaProfesional(String tarjetaProfesional);

}
