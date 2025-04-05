package com.gestVet.app.persistence.crud;

import com.gestVet.app.persistence.entity.Usuario;
import org.springframework.data.repository.CrudRepository;

public interface UsuarioCrudRepository extends CrudRepository<Usuario, Long> {
}
