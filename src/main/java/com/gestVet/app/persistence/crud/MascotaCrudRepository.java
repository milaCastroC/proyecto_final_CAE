package com.gestVet.app.persistence.crud;

import com.gestVet.app.persistence.entity.Mascota;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface MascotaCrudRepository extends CrudRepository<Mascota, Long> {
   // List<Mascota> findByPropietarioId(Long propietarioId);
    // boolean existsByPropietarioId(Long propietarioId);
    
    List<Mascota> findByPropietario_PersonaId(Long clienteId);
    boolean existsByPropietario_PersonaId(Long clienteId);


    @Query("SELECT CASE WHEN COUNT(c) > 0 THEN true ELSE false END FROM Cita c WHERE c.mascota.mascotaId = :mascotaId")
    boolean existsByMascotaIdAndCitasIsNotEmpty(Long mascotaId);
}