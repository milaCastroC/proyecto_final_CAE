package com.gestVet.app.persistence.crud;

import com.gestVet.app.persistence.entity.Cita;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.time.LocalDate;

public interface CitaCrudRepository extends CrudRepository<Cita, Long> {

    @Query(value = "SELECT * FROM cita WHERE veterinario_id = ?1", nativeQuery = true)
    Iterable<Cita> findByVeterinarioId(Long id);

    Iterable<Cita> findByEstado(String estado);

    Iterable<Cita> findByFecha(LocalDate fecha);

    @Query(value = "SELECT * FROM cita WHERE mascota_id = ?1", nativeQuery = true)
    Iterable<Cita> findByMascotaId(Long id);

    @Query(value = "SELECT * FROM cita WHERE veterinario_id = ?1 && fecha = ?2 && estado = ?3", nativeQuery = true)
    Iterable<Cita> findByVeterinarioIdAndFechaAndEstado(Long vetId, LocalDate fecha, String estado);

    long count();
}
