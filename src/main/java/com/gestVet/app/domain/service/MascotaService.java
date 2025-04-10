package com.gestVet.app.domain.service;

import com.gestVet.app.domain.dto.MascotaDTO;
import com.gestVet.app.domain.repository.MascotaRepository;
import com.gestVet.app.domain.repository.ClienteRepository;
import com.gestVet.app.exceptions.PropietarioNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class MascotaService {

    private final MascotaRepository mascotaRepository;
    private final ClienteRepository clienteRepository;

    @Autowired
    public MascotaService(MascotaRepository mascotaRepository, ClienteRepository clienteRepository) {
        this.mascotaRepository = mascotaRepository;
        this.clienteRepository = clienteRepository;
    }

    @Transactional(readOnly = true)
    public Iterable<MascotaDTO> findAll() {
        return mascotaRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Optional<MascotaDTO> findById(Long id) {
        return mascotaRepository.findById(id);
    }

    @Transactional
    public MascotaDTO save(MascotaDTO mascotaDTO) {
        if (!clienteRepository.existsById(mascotaDTO.getPropietarioId())) {
            throw new PropietarioNotFoundException();
        }
        return mascotaRepository.save(mascotaDTO);
    }

    @Transactional
    public MascotaDTO update(MascotaDTO mascotaDTO) {
        if (!mascotaRepository.existsById(mascotaDTO.getMascotaId())) {
            throw new IllegalArgumentException("La mascota no existe");
        }
        return mascotaRepository.update(mascotaDTO);
    }

    @Transactional
    public void delete(Long id) {
        if(!mascotaRepository.existsById(id)){
            throw new IllegalArgumentException("Mascota no encontrada");
        }
        System.out.println(mascotaRepository.existsByMascotaIdAndCitasIsNotEmpty(id));
        if (mascotaRepository.existsByMascotaIdAndCitasIsNotEmpty(id)) {
            throw new IllegalStateException("No se puede eliminar: La mascota tiene citas asociadas");
        }
        mascotaRepository.delete(id);
    }

    @Transactional(readOnly = true)
    public List<MascotaDTO> findByPropietarioId(Long propietarioId) {
        if (!clienteRepository.existsById(propietarioId)) {
            throw new IllegalArgumentException("El propietario no existe");
        }
        return mascotaRepository.findByPropietarioId(propietarioId);
    }

    // Método para ItemHistorialService.
    @Transactional(readOnly = true)
    public boolean existsById(Long id) {
        return mascotaRepository.existsById(id);
    }

}