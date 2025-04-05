package com.gestVet.app.persistence.crud;

import com.gestVet.app.persistence.entity.Usuario;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface UsuarioCrudRepository extends CrudRepository<Usuario, Long> {
    @Query("SELECT u FROM Usuario u WHERE u.username = :username AND u.password = :password")
    Optional<Usuario> login(@Param("username") String username, @Param("password") String password);
}
