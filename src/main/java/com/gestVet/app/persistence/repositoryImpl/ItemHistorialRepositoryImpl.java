package com.gestVet.app.persistence.repositoryImpl;

import com.gestVet.app.domain.dto.ItemHistorialDTO;
import com.gestVet.app.domain.repository.ItemHistorialRepository;
import com.gestVet.app.persistence.crud.ItemHistorialCrudRepository;
import com.gestVet.app.persistence.entity.ItemHistorial;
import com.gestVet.app.persistence.mapper.ItemHistorialMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class ItemHistorialRepositoryImpl implements ItemHistorialRepository {

    private final ItemHistorialCrudRepository crudRepository;
    private final ItemHistorialMapper mapper;

    @Override
    public ItemHistorialDTO save(ItemHistorialDTO itemDTO) {
        ItemHistorial entity = mapper.toEntity(itemDTO);
        return mapper.toDto(crudRepository.save(entity));
    }

    @Override
    public Optional<ItemHistorialDTO> findById(Long id) {
        return crudRepository.findById(id)
                .map(mapper::toDto);
    }

    @Override
    public List<ItemHistorialDTO> findByMascotaId(Long mascotaId) {
        return mapper.toDtoList(crudRepository.findByMascota_MascotaId(mascotaId));
    }

    @Override
    public List<ItemHistorialDTO> findAll() {
        return mapper.toDtoList(crudRepository.findAll());
    }

    @Override
    public void deleteById(Long id) {
        crudRepository.deleteById(id);
    }

    @Override
    public boolean existsById(Long id) {
        return crudRepository.existsById(id);
    }

    @Override
    public List<ItemHistorialDTO> findByCitaId(Long citaId) {
        return mapper.toDtoList(crudRepository.findByCitaId(citaId));
    }

    @Override
    public List<ItemHistorialDTO> findByTipo(String tipo) {
        return mapper.toDtoList(crudRepository.findByTipo(tipo));
    }

    @Override
    public List<ItemHistorialDTO> findByFechaBetween(LocalDate fechaInicio, LocalDate fechaFin) {
        return mapper.toDtoList(crudRepository.findByFechaBetween(fechaInicio, fechaFin));
    }

    @Override
    public List<ItemHistorialDTO> findByMascotaIdAndTipo(Long mascotaId, String tipo) {
        return mapper.toDtoList(crudRepository.findByMascota_MascotaIdAndTipo(mascotaId, tipo));
    }

    @Override
    public long countByMascotaId(Long mascotaId) {
        return crudRepository.countByMascotaId(mascotaId);
    }
}