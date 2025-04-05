package com.gestVet.app.persistence.crud;

import com.gestVet.app.persistence.entity.ItemHistorial;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface ItemHistorialCrudRepository extends CrudRepository<ItemHistorial, Long> {

    @Override
    List<ItemHistorial> findAll();

    //List<ItemHistorial> findByMascotaId(Long mascotaId);
    List<ItemHistorial> findByMascota_MascotaId(Long mascotaId);

    @Query(value = "SELECT * FROM item_historial WHERE cita_id = :citaId", nativeQuery = true)
    List<ItemHistorial> findByCitaId(@Param("citaId") Long citaId);

    @Query(value = "SELECT * FROM item_historial WHERE tipo = :tipo", nativeQuery = true)
    List<ItemHistorial> findByTipo(@Param("tipo") String tipo);

    @Query(value = "SELECT * FROM item_historial WHERE fecha BETWEEN :fechaInicio AND :fechaFin", nativeQuery = true)
    List<ItemHistorial> findByFechaBetween(@Param("fechaInicio") LocalDate fechaInicio, @Param("fechaFin") LocalDate fechaFin);

    //List<ItemHistorial> findByMascotaIdAndTipo(Long mascotaId, String tipo);
    List<ItemHistorial> findByMascota_MascotaIdAndTipo(Long mascotaId, String tipo);

    @Query(value = "SELECT COUNT(*) FROM item_historial WHERE mascota_id = :mascotaId", nativeQuery = true)
    long countByMascotaId(@Param("mascotaId") Long mascotaId);

}