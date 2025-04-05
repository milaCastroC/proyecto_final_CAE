package com.gestVet.app.domain.repository;

import com.gestVet.app.domain.dto.ItemHistorialDTO;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface ItemHistorialRepository {
    ItemHistorialDTO save(ItemHistorialDTO itemDTO);
    Optional<ItemHistorialDTO> findById(Long id);
    List<ItemHistorialDTO> findByMascotaId(Long mascotaId);
    List<ItemHistorialDTO> findAll();
    void deleteById(Long id);
    boolean existsById(Long id);
    
    // Métodos adicionales que se usan en el servicio
    List<ItemHistorialDTO> findByTipo(String tipo);
    List<ItemHistorialDTO> findByFechaBetween(LocalDate inicio, LocalDate fin);
    List<ItemHistorialDTO> findByCitaId(Long citaId);
    List<ItemHistorialDTO> findByMascotaIdAndTipo(Long mascotaId, String tipo);
    long countByMascotaId(Long mascotaId);
}