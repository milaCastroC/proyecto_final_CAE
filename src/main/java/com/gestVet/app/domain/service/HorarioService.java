package com.gestVet.app.domain.service;

import com.gestVet.app.domain.dto.HorarioDTO;
import com.gestVet.app.domain.repository.HorarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class HorarioService {

    @Autowired
    private HorarioRepository horarioRepository;

    // Consultar todos los registros
    public Iterable<HorarioDTO> findAll(){
        return horarioRepository.findAll();
    }

    // Consultar por ID
    public Optional<HorarioDTO> findById(Long id) {
        return horarioRepository.findById(id);
    }

    // Guardar un registro
    public HorarioDTO save(HorarioDTO horarioDTO) {
        return horarioRepository.update(horarioDTO);
    }

    // Actualizar un registro
    public HorarioDTO update(HorarioDTO horarioDTO) {
        return horarioRepository.update(horarioDTO);
    }

    // Eliminar un registro
    public boolean delete(Long id) {
        if(existsById(id)) {
            return horarioRepository.delete(id);
        }
        return false;
    }

    // Validar si existe por ID
    public boolean existsById(Long id) {
        return horarioRepository.existsById(id);
    }

    // Validar las horas
    public boolean validarHorasValidas(LocalDateTime horaInicio, LocalDateTime horaFin) {
        if(horaInicio == null || horaFin == null) {
            return false;
        }
        return horaFin.isAfter(horaInicio);
    }

}
