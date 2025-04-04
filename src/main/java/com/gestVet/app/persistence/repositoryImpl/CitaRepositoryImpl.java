package com.gestVet.app.persistence.repositoryImpl;

import com.gestVet.app.domain.dto.CitaDTO;
import com.gestVet.app.domain.repository.CitaRepository;
import com.gestVet.app.persistence.crud.CitaCrudRepository;
import com.gestVet.app.persistence.entity.Cita;
import com.gestVet.app.persistence.mapper.CitaMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class CitaRepositoryImpl implements CitaRepository {

    @Autowired
    private CitaCrudRepository citaCrudRepository;

    @Autowired
    private CitaMapper citaMapper;

    @Override
    public Iterable<CitaDTO> findAll() {
        Iterable<Cita> citas = citaCrudRepository.findAll();
        return ((List<Cita>) citas)
                .stream()
                .map(citaMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<CitaDTO> findById(Long id) {
        return citaCrudRepository.findById(id)
                .map(citaMapper::toDto);
    }

    @Override
    public CitaDTO save(CitaDTO citaDTO) {
        Cita cita = citaMapper.toEntity(citaDTO);
        Cita savedCita = citaCrudRepository.save(cita);
        return citaMapper.toDto(savedCita);
    }

    @Override
    public CitaDTO updateEstado(CitaDTO citaDTO) {
        return save(citaDTO);
    }

    @Override
    public Iterable<CitaDTO> findByVeterinarioId(Long id) {
        Iterable<Cita> citas = citaCrudRepository.findByVeterinarioId(id);
        return ((List<Cita>) citas)
                .stream()
                .map(citaMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public Iterable<CitaDTO> findByMascotaId(Long id) {
        Iterable<Cita> citas = citaCrudRepository.findByMascotaId(id);
        return ((List<Cita>) citas)
                .stream()
                .map(citaMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public Iterable<CitaDTO> findByFecha(LocalDateTime fecha) {
        Iterable<Cita> citas = citaCrudRepository.findByFecha(fecha);
        return ((List<Cita>) citas)
                .stream()
                .map(citaMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public Iterable<CitaDTO> findByEstado(String estado) {
        Iterable<Cita> citas = citaCrudRepository.findByEstado(estado);
        return ((List<Cita>) citas)
                .stream()
                .map(citaMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public Iterable<CitaDTO> findByVeterinarioIdAndFechaAndEstado(Long vetId, LocalDateTime fecha, String estado) {
        Iterable<Cita> citas = citaCrudRepository.findByVeterinarioIdAndFechaAndEstado(vetId, fecha, estado);
        return ((List<Cita>) citas)
                .stream()
                .map(citaMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public boolean existsById(Long id) {
        return citaCrudRepository.existsById(id);
    }
}
