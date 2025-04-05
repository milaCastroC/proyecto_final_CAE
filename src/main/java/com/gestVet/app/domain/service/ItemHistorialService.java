package com.gestVet.app.domain.service;

import com.gestVet.app.domain.dto.CitaDTO;
import com.gestVet.app.domain.dto.ItemHistorialDTO;
import com.gestVet.app.domain.repository.ItemHistorialRepository;
import com.gestVet.app.exceptions.*;
import com.gestVet.app.persistence.mapper.ItemHistorialMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class ItemHistorialService {

    @Autowired
    private ItemHistorialRepository repository;

    @Autowired
    private CitaService citaService;

    @Autowired
    private MascotaService mascotaService;

    @Autowired
    private ItemHistorialMapper mapper;

    // Crear desde cita
    @Transactional
    public ItemHistorialDTO createFromCita(Long citaId, ItemHistorialDTO itemDTO)
            throws CitaNotFoundException, CitaNotAttendedException, MascotaCitaMismatchException {

        CitaDTO cita = citaService.findById(citaId)
                .orElseThrow(CitaNotFoundException::new);

        if (!"Atendida".equals(cita.getEstado())) {
            throw new CitaNotAttendedException();
        }

        if (!itemDTO.getMascotaId().equals(cita.getMascotaId())) {
            throw new MascotaCitaMismatchException();
        }

        itemDTO.setFecha(cita.getFecha().toLocalDate());
        itemDTO.setTipoCita(cita.getTipoCita());
        itemDTO.setCitaId(citaId);

        return repository.save(itemDTO);
    }

    // Obtener por ID
    @Transactional(readOnly = true)
    public Optional<ItemHistorialDTO> findById(Long id) {
        return repository.findById(id);
    }

    // Obtener por ID con excepción
    @Transactional(readOnly = true)
    public ItemHistorialDTO getById(Long id) throws ItemHistorialNotFoundException {
        return repository.findById(id)
                .orElseThrow(ItemHistorialNotFoundException::new);
    }

    // Listar por mascota
    @Transactional(readOnly = true)
    public List<ItemHistorialDTO> findByMascotaId(Long mascotaId) throws MascotaNotFoundException {
        if (!mascotaService.existsById(mascotaId)) {
            throw new MascotaNotFoundException();
        }
        return repository.findByMascotaId(mascotaId);
    }

    // Actualizar
    @Transactional
    public ItemHistorialDTO update(Long id, ItemHistorialDTO itemDTO)
            throws ItemHistorialNotFoundException {

        if (!repository.existsById(id)) {
            throw new ItemHistorialNotFoundException();
        }

        itemDTO.setItemHistorialId(id);
        return repository.save(itemDTO);
    }

    // Eliminar
    @Transactional
    public void deleteById(Long id) throws ItemHistorialNotFoundException {
        if (!repository.existsById(id)) {
            throw new ItemHistorialNotFoundException();
        }
        repository.deleteById(id);
    }

    // Listar todos
    @Transactional(readOnly = true)
    public List<ItemHistorialDTO> findAll() {
        return repository.findAll();
    }

    // Buscar por tipo de consulta
    @Transactional(readOnly = true)
    public List<ItemHistorialDTO> findByTipo(String tipo) {
        return repository.findByTipo(tipo);
    }

    // Buscar por rango de fechas
    @Transactional(readOnly = true)
    public List<ItemHistorialDTO> findByFechaBetween(LocalDate inicio, LocalDate fin) {
        return repository.findByFechaBetween(inicio, fin);
    }

    // Contar por mascota
    @Transactional(readOnly = true)
    public long countByMascotaId(Long mascotaId) throws MascotaNotFoundException {
        if (!mascotaService.existsById(mascotaId)) {
            throw new MascotaNotFoundException();
        }
        return repository.countByMascotaId(mascotaId);
    }

    @Transactional
    public ItemHistorialDTO create(ItemHistorialDTO itemDTO) throws MascotaNotFoundException {
        if (!mascotaService.existsById(itemDTO.getMascotaId())) {
            throw new MascotaNotFoundException();
        }
        return repository.save(itemDTO);
    }

}