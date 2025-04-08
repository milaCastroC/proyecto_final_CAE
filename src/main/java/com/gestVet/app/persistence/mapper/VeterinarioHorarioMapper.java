package com.gestVet.app.persistence.mapper;

import com.gestVet.app.domain.dto.VeterinarioHorarioDTO;
import com.gestVet.app.persistence.entity.*;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.InheritInverseConfiguration;

@Mapper(componentModel = "spring")
public interface VeterinarioHorarioMapper {

    // Mapeo de VeterinarioHorario a VeterinarioHorarioDTO
    @Mapping(source = "veterinarioHorarioId", target = "veterinarioHorarioId")
    @Mapping(source = "veterinario", target = "veterinarioId")
    @Mapping(source = "horario", target = "horarioId")
    @Mapping(source = "diaSemana", target = "diaSemana")
    VeterinarioHorarioDTO toDto(VeterinarioHorario veterinarioHorario);

    // Mapeo inverso de VeterinarioHorarioDTO a VeterinarioHorario
    @InheritInverseConfiguration
    @Mapping(target = "veterinario", source = "veterinarioId")
    @Mapping(target = "horario", source = "horarioId")
    VeterinarioHorario toEntity(VeterinarioHorarioDTO veterinarioHorarioDTO);

    // Métodos de conversión para las relaciones

    default Long mapVeterinario(Veterinario veterinario) {
        return veterinario != null ? veterinario.getPersonaId() : null;
    }

    default Veterinario mapVeterinario(Long veterinarioId) {
        if (veterinarioId != null) {
            Veterinario veterinario = new Veterinario();
            veterinario.setPersonaId(veterinarioId);
            return veterinario;
        }
        return null;
    }

    default Long mapHorario(Horario horario) {
        return horario != null ? horario.getHorarioId() : null;
    }

    default Horario mapHorario(Long horarioId) {
        if (horarioId != null) {
            Horario horario = new Horario();
            horario.setHorarioId(horarioId);
            return horario;
        }
        return null;
    }
}