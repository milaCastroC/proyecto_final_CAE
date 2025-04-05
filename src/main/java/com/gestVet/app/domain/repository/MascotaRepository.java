package com.gestVet.app.domain.repository;

import com.gestVet.app.domain.dto.MascotaDTO;
import java.util.List;
import java.util.Optional;

public interface MascotaRepository {
    
    // CRUD Básico
    Iterable<MascotaDTO> findAll();
    Optional<MascotaDTO> findById(Long id);
    MascotaDTO save(MascotaDTO mascotaDTO);
    MascotaDTO update(MascotaDTO mascotaDTO);
    boolean delete(Long id);
    
    // Validaciones
    boolean existsById(Long id);
    long count();
    
    // Métodos específicos
    List<MascotaDTO> findByPropietarioId(Long propietarioId);
    boolean existsByPropietarioId(Long propietarioId);

    boolean existsByMascotaIdAndCitasIsNotEmpty(Long mascotaId);

    
}