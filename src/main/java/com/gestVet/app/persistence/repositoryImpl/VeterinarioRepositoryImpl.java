package com.gestVet.app.persistence.repositoryImpl;

import com.gestVet.app.domain.dto.VeterinarioDTO;
import com.gestVet.app.domain.repository.VeterinarioRepository;
import com.gestVet.app.persistence.crud.VeterinarioCrudRepository;
import com.gestVet.app.persistence.entity.Veterinario;
import com.gestVet.app.persistence.mapper.VeterinarioMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class VeterinarioRepositoryImpl implements VeterinarioRepository {

    @Autowired
    VeterinarioCrudRepository veterinarioCrudRepository;

    @Autowired
    VeterinarioMapper veterinarioMapper;

    @Override
    public Iterable<VeterinarioDTO> findAll() {
        Iterable<Veterinario> trips = veterinarioCrudRepository.findAll();

        return ((List<Veterinario>) trips)
                .stream()
                .map(veterinarioMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<VeterinarioDTO> findById(Long id) {
        Optional<Veterinario> vet = veterinarioCrudRepository.findById(id);
        return vet.map(veterinarioMapper::toDto);
    }

    @Override
    public VeterinarioDTO save(VeterinarioDTO veterinarioDTO) {
        Veterinario vet = veterinarioMapper.toEntity(veterinarioDTO);
        Veterinario savedVet = veterinarioCrudRepository.save(vet);
        return veterinarioMapper.toDto(savedVet);
    }

    @Override
    public VeterinarioDTO update(VeterinarioDTO veterinarioDTO) {
        Veterinario vet = veterinarioMapper.toEntity(veterinarioDTO);
        Veterinario savedVet = veterinarioCrudRepository.save(vet);
        return veterinarioMapper.toDto(savedVet);
    }

    @Override
    public boolean delete(Long id) {
        if (existsById(id)){
            veterinarioCrudRepository.deleteById(id);
            return true;
        }
        throw new NullPointerException("No existe el veterinario");
    }

    @Override
    public boolean existsById(Long id) {
        return veterinarioCrudRepository.existsById(id);
    }

    @Override
    public long count() {
        return veterinarioCrudRepository.count();
    }

    @Override
    public boolean existsByTarjetaProfesional(String tarjetaProfesional) {
        return veterinarioCrudRepository.existsByTarjetaProfesional(tarjetaProfesional);
    }
}
