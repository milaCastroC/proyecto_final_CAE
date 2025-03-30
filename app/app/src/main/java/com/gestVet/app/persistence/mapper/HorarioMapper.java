package com.gestVet.app.persistence.mapper;

import com.gestVet.app.domain.dto.HorarioDTO;
import com.gestVet.app.persistence.entity.Horario;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.InheritInverseConfiguration;

@Mapper(componentModel = "spring")
public interface HorarioMapper {

    // Mapeo de Horario a HorarioDTO
    @Mapping(source = "horarioId", target = "horarioId")
    @Mapping(source = "horaInicio", target = "horaInicio")
    @Mapping(source = "horaFin", target = "horaFin")
    @Mapping(target = "citas", ignore = true)
    @Mapping(target = "veterinarioHorarios", ignore = true)
    HorarioDTO toDto(Horario horario);

    // Mapeo inverso de HorarioDTO a Horario
    @InheritInverseConfiguration
    @Mapping(target = "citas", ignore = true)
    @Mapping(target = "veterinarioHorarios", ignore = true)
    Horario toEntity(HorarioDTO horarioDTO);
}