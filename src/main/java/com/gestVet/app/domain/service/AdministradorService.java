package com.gestVet.app.domain.service;

import com.gestVet.app.domain.dto.AdministradorDTO;
import com.gestVet.app.domain.repository.AdministradorRepository;
import com.gestVet.app.domain.repository.UsuarioRepository;
import jakarta.persistence.EntityNotFoundException;
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
        //Validar que no exista otra persona con la misma identificación
        if (usuarioRepository.existsByIdentificacionAndTipoIdentificacion(
                adminDTO.getIdentificacion(),
                adminDTO.getTipoIdentificacion())) {
            throw new IllegalArgumentException("Ya existe una persona con esta identificación y tipo");
        }

        // Validar que el usuario no esté ya asignado a otro administrador
        validarUsuarioUnico(adminDTO.getPersonaId());

        if(administradorRepository.existsByCargoAndArea(adminDTO.getCargo(), adminDTO.getArea())) {
            throw new IllegalArgumentException("Ya existe un administrador con ese cargo en el área especificada");
        }
        return administradorRepository.save(adminDTO);
    }

    //Actualizar un administrador
    public AdministradorDTO update(Long id, AdministradorDTO adminDTO) {

        AdministradorDTO adminExistente = administradorRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Administrador no encontrado"));

        //Actualizar solo los campos permitidos (telefono, email, direccion, cargo, area)
        if (adminDTO.getTelefono() != null) {
            adminExistente.setTelefono(adminDTO.getTelefono());
        }
        if (adminDTO.getEmail() != null) {
            adminExistente.setEmail(adminDTO.getEmail());
        }
        if (adminDTO.getDireccion() != null) {
            adminExistente.setDireccion(adminDTO.getDireccion());
        }

        // Validar y actualizar cargo y área
        if (adminDTO.getCargo() != null || adminDTO.getArea() != null) {
            String nuevoCargo = adminDTO.getCargo() != null ? adminDTO.getCargo() : adminExistente.getCargo();
            String nuevaArea = adminDTO.getArea() != null ? adminDTO.getArea() : adminExistente.getArea();

            // Verificar si existe otro administrador con ese cargo y área (excluyendo el actual)
            if (administradorRepository.existsByCargoAndAreaAndIdNot(nuevoCargo, nuevaArea, id)) {
                throw new IllegalArgumentException("Ya existe otro administrador con ese cargo en el área especificada");
            }

            // Actualizar campos
            adminExistente.setCargo(nuevoCargo);
            adminExistente.setArea(nuevaArea);
        }

        return administradorRepository.update(adminExistente);
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
