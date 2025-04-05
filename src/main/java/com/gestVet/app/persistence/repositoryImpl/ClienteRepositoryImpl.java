package com.gestVet.app.persistence.repositoryImpl;

import com.gestVet.app.domain.dto.ClienteDTO;
import com.gestVet.app.domain.repository.ClienteRepository;
import com.gestVet.app.persistence.crud.ClienteCrudRepository;
import com.gestVet.app.persistence.entity.Cliente;
import com.gestVet.app.persistence.mapper.ClienteMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class ClienteRepositoryImpl implements ClienteRepository {

    @Autowired
    private ClienteCrudRepository clienteCrudRepository;

    @Autowired
    private ClienteMapper clienteMapper;

    @Override
    public Iterable<ClienteDTO> findAll() {
        List<Cliente> clientes = (List<Cliente>) clienteCrudRepository.findAll();
        return clientes.stream()
                .map(clienteMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<ClienteDTO> findById(Long id) {
        return clienteCrudRepository.findById(id)
                .map(clienteMapper::toDto);
    }

    @Override
    public ClienteDTO save(ClienteDTO clienteDTO) {
        Cliente cliente = clienteMapper.toEntity(clienteDTO);
        Cliente savedCliente = clienteCrudRepository.save(cliente);
        return clienteMapper.toDto(savedCliente);
    }

    @Override
    public ClienteDTO update(ClienteDTO clienteDTO) {
        return save(clienteDTO); // Reutiliza el método save
    }

    @Override
    public boolean delete(Long id) {
        if (clienteCrudRepository.existsById(id)) {
            clienteCrudRepository.deleteById(id);
            return true;
        }
        return false;
    }

    @Override
    public boolean existsById(Long id) {
        return clienteCrudRepository.existsById(id);
    }

    @Override
    public long count() {
        return clienteCrudRepository.count();
    }

    @Override
    public boolean existsByPersonaId(Long personaId) {
        if(!clienteCrudRepository.existsById(personaId)){
            return true;
        }
        // Si no existe, retorna false  
        return false;
    }

    @Override
    public boolean existsByClienteIdAndMascotasIsNotEmpty(Long clienteId) {
        return clienteCrudRepository.existsMascotasByClienteId(clienteId);
    }

    /**@Override
    public boolean existsByPersonaId(Long personaId) {
        return clienteCrudRepository.existsByPersonaId(personaId);
    }

    @Override
    public boolean existsByClienteIdAndMascotasIsNotEmpty(Long clienteId) {
        return clienteCrudRepository.existsByClienteIdAndMascotasIsNotEmpty(clienteId);
    }
        */
}