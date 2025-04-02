package com.gestVet.app.domain.service;

import com.gestVet.app.domain.dto.VeterinarioDTO;
import com.gestVet.app.domain.repository.VeterinarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class VeterinarioService {

    @Autowired
    private VeterinarioRepository veterinarioRepository;

    // Consultar todos los registros
    public Iterable<VeterinarioDTO> findAll(){
        return veterinarioRepository.findAll();
    }

    // Consultar por ID
    public Optional<VeterinarioDTO> findById(Long id) {
        return veterinarioRepository.findById(id);
    }

    // Guardar un registro
    public VeterinarioDTO save(VeterinarioDTO veterinarioDTO) {
        if(validarTarjetaProfesional(veterinarioDTO.getTarjetaProfesional())) {
            return veterinarioRepository.update(veterinarioDTO);
        }
        throw new IllegalArgumentException("La tarjeta profesional ya se encuentra registrada");
    }

    // Actualizar un registro
    public VeterinarioDTO update(VeterinarioDTO veterinarioDTO) {
        if(!existsById(veterinarioDTO.getVeterinarioId())){
            throw new IllegalArgumentException("El veterinario que busca no existe");
        }
        VeterinarioDTO vetOriginal = findById(veterinarioDTO.getVeterinarioId()).orElse(null);

        if(!vetOriginal.getTarjetaProfesional().equals(veterinarioDTO.getTarjetaProfesional())) {
            if(validarTarjetaProfesional(veterinarioDTO.getTarjetaProfesional())) {
                return veterinarioRepository.update(veterinarioDTO);
            }
            throw new IllegalArgumentException("La tarjeta profesional ya se encuentra registrada");
        }
        return veterinarioRepository.update(veterinarioDTO);
    }

    // Eliminar un registro
    public boolean delete(Long id) {
        return veterinarioRepository.delete(id);
    }

    // Validar si existe por ID
    public boolean existsById(Long id) {
        return veterinarioRepository.existsById(id);
    }

    // Contar todos los registros
    public long count() {
        return veterinarioRepository.count();
    }

    // Validar tarjeta profesional
    public boolean validarTarjetaProfesional(String tarjetaProfesional) {
        return !veterinarioRepository.existsByTarjetaProfesional(tarjetaProfesional);
    }

    // TODO validar que el usuario exista
    // TODO validar que sólo pueda haber un usuario por veterinario
}
