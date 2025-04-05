package com.gestVet.app.domain.service;

import com.gestVet.app.domain.dto.AdministradorDTO;
import com.gestVet.app.domain.repository.AdministradorRepository;
import com.gestVet.app.domain.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AdministradorService {

    @Autowired
    private AdministradorRepository administradorRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    //Consultar todos los registros
    public Iterable<AdministradorDTO> findAll() {
        return administradorRepository.findAll();
    }

    //Consultar por Id
    public Optional<AdministradorDTO> findById(Long id) {
        return administradorRepository.findById(id);
    }

    //Guardar un administrador
    public AdministradorDTO save(AdministradorDTO adminDTO) {
        // Validar que el usuario exista
        validarExistenciaUsuario(adminDTO.getPersonaId());
        // Validar que el usuario no esté ya asignado a otro administrador
        validarUsuarioUnico(adminDTO.getPersonaId());

        if(administradorRepository.existsByCargoAndArea(adminDTO.getCargo(), adminDTO.getArea())) {
            throw new IllegalArgumentException("Ya existe un administrador con ese cargo en el área especificada");
        }
        return administradorRepository.save(adminDTO);
    }

    //Actualizar un administrador
    public AdministradorDTO update(Long id, AdministradorDTO adminDTO) {
        if (!administradorRepository.existsById(adminDTO.getPersonaId())) {
            throw new IllegalArgumentException("Administrador no encontrado");
        }

        // Validar que el usuario exista
        validarExistenciaUsuario(adminDTO.getPersonaId());

        // Validar que el usuario no esté asignado a otro administrador (excepto este mismo)
        validarUsuarioUnicoParaActualizacion(id, adminDTO.getPersonaId());

        adminDTO.setPersonaId(id);
        return administradorRepository.update(adminDTO);
    }

    //Eliminar un administrador
    public boolean delete(Long id) {
        return administradorRepository.delete(id);
    }

    private void validarExistenciaUsuario(Long usuarioId) {
        if (!usuarioRepository.existsById(usuarioId)) {
            throw new IllegalArgumentException("El usuario especificado no existe");
        }
    }

    private void validarUsuarioUnico(Long usuarioId) {
        if (administradorRepository.existsByUsuarioId(usuarioId)) {
            throw new IllegalStateException("Este usuario ya está asignado a otro administrador");
        }
    }

    private void validarUsuarioUnicoParaActualizacion(Long adminId, Long usuarioId) {
        Optional<AdministradorDTO> adminExistente = administradorRepository.findByUsuarioId(usuarioId);
        if (adminExistente.isPresent() && !adminExistente.get().getPersonaId().equals(adminId)) {
            throw new IllegalStateException("Este usuario ya está asignado a otro administrador");
        }
    }
}
