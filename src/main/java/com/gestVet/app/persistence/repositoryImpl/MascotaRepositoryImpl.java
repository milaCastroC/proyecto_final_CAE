package com.gestVet.app.persistence.repositoryImpl;

import com.gestVet.app.domain.dto.MascotaDTO;
import com.gestVet.app.domain.repository.MascotaRepository;
import com.gestVet.app.persistence.crud.MascotaCrudRepository;
import com.gestVet.app.persistence.entity.Mascota;
import com.gestVet.app.persistence.mapper.MascotaMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class MascotaRepositoryImpl implements MascotaRepository {

    @Autowired
    private MascotaCrudRepository mascotaCrudRepository;

    @Autowired
    private MascotaMapper mascotaMapper;

    @Override
    public Iterable<MascotaDTO> findAll() {
        List<Mascota> mascotas = (List<Mascota>) mascotaCrudRepository.findAll();
        return mascotas.stream()
                .map(mascotaMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<MascotaDTO> findById(Long id) {
        Optional<Mascota> mascota = mascotaCrudRepository.findById(id);
        return mascota.map(mascotaMapper::toDto);
    }

    @Override
    public MascotaDTO save(MascotaDTO mascotaDTO) {
        Mascota mascota = mascotaMapper.toEntity(mascotaDTO);
        Mascota savedMascota = mascotaCrudRepository.save(mascota);
        return mascotaMapper.toDto(savedMascota);
    }

    @Override
    public MascotaDTO update(MascotaDTO mascotaDTO) {
        // Reutilizamos save() para actualizar, igual que en Veterinario
        return save(mascotaDTO);
    }

    @Override
    public boolean delete(Long id) {
        if (existsById(id)) {
            mascotaCrudRepository.deleteById(id);
            return true;
        }
        return false;
    }

    @Override
    public boolean existsById(Long id) {
        return mascotaCrudRepository.existsById(id);
    }

    @Override
    public long count() {
        return mascotaCrudRepository.count();
    }

    @Override
    public List<MascotaDTO> findByPropietarioId(Long propietarioId) {
        List<Mascota> mascotas = mascotaCrudRepository.findByPropietario_PersonaId(propietarioId);
        return mascotas.stream()
                .map(mascotaMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public boolean existsByPropietarioId(Long propietarioId) {
        return mascotaCrudRepository.existsByPropietario_PersonaId(propietarioId);
    }

    @Override
    public boolean existsByMascotaIdAndCitasIsNotEmpty(Long mascotaId) {
        return mascotaCrudRepository.existsByMascotaIdAndCitasIsNotEmpty(mascotaId);
    }
}