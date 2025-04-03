package com.gestVet.app.persistence.repositoryImpl;

import com.gestVet.app.domain.dto.HorarioDTO;
import com.gestVet.app.domain.dto.VeterinarioDTO;
import com.gestVet.app.domain.repository.HorarioRepository;
import com.gestVet.app.domain.repository.VeterinarioRepository;
import com.gestVet.app.persistence.crud.HorarioCrudRepository;
import com.gestVet.app.persistence.entity.Horario;
import com.gestVet.app.persistence.mapper.HorarioMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class HorarioRepositoryImpl implements HorarioRepository {

    @Autowired
    private HorarioCrudRepository horarioCrudRepository;

    @Autowired
    private HorarioMapper horarioMapper;

    @Override
    public Iterable<HorarioDTO> findAll() {
        Iterable<Horario> horarios =  horarioCrudRepository.findAll();
        return ((List<Horario>) horarios)
                .stream()
                .map(horarioMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<HorarioDTO> findById(Long id) {
        return horarioCrudRepository.findById(id)
                .map(horarioMapper::toDto);
    }

    @Override
    public HorarioDTO save(HorarioDTO horarioDTO) {
        Horario horario = horarioMapper.toEntity(horarioDTO);
        Horario savedHorario = horarioCrudRepository.save(horario);
        return horarioMapper.toDto(savedHorario);
    }

    @Override
    public HorarioDTO update(HorarioDTO horarioDTO) {
        return save(horarioDTO);
    }

    @Override
    public boolean delete(Long id) {
        if (existsById(id)) {
            horarioCrudRepository.deleteById(id);
            return true;
        }
        return false;
    }

    @Override
    public boolean existsById(Long id) {
        return horarioCrudRepository.existsById(id);
    }

}
