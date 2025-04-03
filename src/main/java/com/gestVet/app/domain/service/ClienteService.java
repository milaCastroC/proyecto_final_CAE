package com.gestVet.app.domain.service;

import com.gestVet.app.domain.dto.ClienteDTO;
import com.gestVet.app.domain.repository.ClienteRepository;
import com.gestVet.app.persistence.exceptions.ClienteNotFoundException;
import com.gestVet.app.persistence.exceptions.PersonaIdDuplicadoException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    // Consultar todos los registros
    public Iterable<ClienteDTO> findAll() {
        return clienteRepository.findAll();
    }

    // Consultar por ID
    public Optional<ClienteDTO> findById(Long id) {
        return clienteRepository.findById(id);
    }

    // Guardar un registro
 
    public ClienteDTO save(ClienteDTO clienteDTO) throws PersonaIdDuplicadoException {
        if (validarPersonaId(clienteDTO.getPersonaId())) {
            return clienteRepository.save(clienteDTO);
        }
        throw new PersonaIdDuplicadoException();
    }

    // Actualizar un registro
    public ClienteDTO update(ClienteDTO clienteDTO) throws ClienteNotFoundException, PersonaIdDuplicadoException {
        if (!existsById(clienteDTO.getClienteId())) {
            throw new ClienteNotFoundException();
        }
        
        ClienteDTO clienteOriginal = findById(clienteDTO.getClienteId()).orElse(null);
        
        if (!clienteOriginal.getPersonaId().equals(clienteDTO.getPersonaId())) {
            if (validarPersonaId(clienteDTO.getPersonaId())) {
                return clienteRepository.update(clienteDTO);
            }
            throw new PersonaIdDuplicadoException();
        }
        return clienteRepository.update(clienteDTO);
    }

    // Eliminar un registro
    public boolean delete(Long id) {
        if (tieneMascotasAsociadas(id)) {
            throw new IllegalStateException("No se puede eliminar: El cliente tiene mascotas asociadas");
        }
        return clienteRepository.delete(id);
    }

    // Validar si existe por ID
    public boolean existsById(Long id) {
        return clienteRepository.existsById(id);
    }

    // Contar todos los registros
    public long count() {
        return clienteRepository.count();
    }

    // Validar que personaId no esté duplicado
    public boolean validarPersonaId(Long personaId) {
        return !clienteRepository.existsByPersonaId(personaId);
    }

    // Validar si el cliente tiene mascotas asociadas
    public boolean tieneMascotasAsociadas(Long clienteId) {
        return clienteRepository.existsByClienteIdAndMascotasIsNotEmpty(clienteId);
    }

    // TODO: Validar que personaId exista en la tabla Persona
}