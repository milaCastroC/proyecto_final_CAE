package com.gestVet.app.persistence.repositoryImpl;

import com.gestVet.app.domain.dto.AdministradorDTO;
import com.gestVet.app.domain.repository.AdministradorRepository;
import com.gestVet.app.persistence.crud.AdministradorCrudRepository;
import com.gestVet.app.persistence.entity.Administrador;
import com.gestVet.app.persistence.mapper.AdministradorMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class AdministradorRepositoryImpl implements AdministradorRepository {

    @Autowired
    AdministradorCrudRepository administradorCrudRepository;

    @Autowired
    AdministradorMapper administradorMapper;

    @Override
    public AdministradorDTO save(AdministradorDTO adminDTO) {
        Administrador admin = administradorMapper.toEntity(adminDTO);
        Administrador savedAdmin = administradorCrudRepository.save(admin);
        return administradorMapper.toDto(savedAdmin);
    }

    @Override
    public AdministradorDTO update(AdministradorDTO adminDTO) {
        Administrador admin = administradorMapper.toEntity(adminDTO);
        Administrador savedAdmin = administradorCrudRepository.save(admin);
        return administradorMapper.toDto(savedAdmin);
    }

    @Override
    public Iterable<AdministradorDTO> findAll() {
        Iterable<Administrador> admins = administradorCrudRepository.findAll();
        return ((List<Administrador>) admins)
                .stream()
                .map(administradorMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<AdministradorDTO> findById(Long id) {
        Optional<Administrador> admin = administradorCrudRepository.findById(id);
        return admin.map(administradorMapper::toDto);
    }

    @Override
    public boolean delete(Long id) {
        if(existsById(id)) {
            administradorCrudRepository.deleteById(id);
            return true;
        }
        return false;
    }

    @Override
    public boolean existsById(Long id) {
        return administradorCrudRepository.existsById(id);
    }

    @Override
    public boolean existsByCargoAndArea(String cargo, String area) {
        return administradorCrudRepository.existsByCargoAndArea(cargo, area);
    }

    @Override
    public boolean existsByUsuarioId(Long usuarioId) {
        return administradorCrudRepository.existsByPersonaId(usuarioId);
    }

    @Override
    public Optional<AdministradorDTO> findByUsuarioId(Long usuarioId) {
        return administradorCrudRepository.findByPersonaId(usuarioId)
                .map(administradorMapper::toDto);
    }

    @Override
    public boolean existsByCargoAndAreaAndIdNot(String cargo, String area, Long id) {
        return ((List<Administrador>) administradorCrudRepository.findAll()).stream()
                .filter(admin -> !admin.getPersonaId().equals(id))
                .anyMatch(admin ->
                        admin.getCargo().equalsIgnoreCase(cargo) &&
                                admin.getArea().equalsIgnoreCase(area)
                );
    }
}
