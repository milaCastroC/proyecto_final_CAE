package com.gestVet.app.persistence.mapper;

import com.gestVet.app.domain.dto.PrivilegioPermisoDTO;
import com.gestVet.app.persistence.entity.*;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.InheritInverseConfiguration;

@Mapper(componentModel = "spring")
public interface PrivilegioPermisoMapper {

    // Mapeo de PrivilegioPermiso a PrivilegioPermisoDTO
    @Mapping(source = "privilegioPermisoId", target = "privilegioPermisoId")
    @Mapping(source = "privilegio", target = "privilegioId")
    @Mapping(source = "permiso", target = "permisoId")
    PrivilegioPermisoDTO toDto(PrivilegioPermiso privilegioPermiso);

    // Mapeo inverso de PrivilegioPermisoDTO a PrivilegioPermiso
    @InheritInverseConfiguration
    @Mapping(target = "privilegio", source = "privilegioId")
    @Mapping(target = "permiso", source = "permisoId")
    PrivilegioPermiso toEntity(PrivilegioPermisoDTO privilegioPermisoDTO);

    // Métodos de conversión para las relaciones

    default Long mapPrivilegio(Privilegio privilegio) {
        return privilegio != null ? privilegio.getPrivilegioId() : null;
    }

    default Privilegio mapPrivilegio(Long privilegioId) {
        if (privilegioId != null) {
            Privilegio privilegio = new Privilegio();
            privilegio.setPrivilegioId(privilegioId);
            return privilegio;
        }
        return null;
    }

    default Long mapPermiso(Permiso permiso) {
        return permiso != null ? permiso.getPermisoId() : null;
    }

    default Permiso mapPermiso(Long permisoId) {
        if (permisoId != null) {
            Permiso permiso = new Permiso();
            permiso.setPermisoId(permisoId);
            return permiso;
        }
        return null;
    }
}