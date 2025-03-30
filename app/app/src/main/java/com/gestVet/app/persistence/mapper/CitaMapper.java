package com.gestVet.app.persistence.mapper;

import com.gestVet.app.domain.dto.CitaDTO;
import com.gestVet.app.persistence.entity.*;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.InheritInverseConfiguration;

@Mapper(componentModel = "spring")
public interface CitaMapper {

    // Mapeo de Cita a CitaDTO
    @Mapping(source = "citaId", target = "citaId")
    @Mapping(source = "mascota", target = "mascotaId")
    @Mapping(source = "horario", target = "horarioId")
    @Mapping(source = "veterinario", target = "veterinarioId")
    @Mapping(source = "fecha", target = "fecha")
    @Mapping(source = "tipoCita", target = "tipoCitaId")
    @Mapping(source = "estado", target = "estado")
    CitaDTO toDto(Cita cita);

    // Mapeo inverso de CitaDTO a Cita
    @InheritInverseConfiguration
    @Mapping(target = "mascota", source = "mascotaId")
    @Mapping(target = "horario", source = "horarioId")
    @Mapping(target = "veterinario", source = "veterinarioId")
    @Mapping(target = "tipoCita", source = "tipoCitaId")
    Cita toEntity(CitaDTO citaDTO);

    // Métodos de mapeo para las relaciones

    default Long mapMascota(Mascota mascota) {
        return mascota != null ? mascota.getMascotaId() : null;
    }

    default Mascota mapMascota(Long mascotaId) {
        if (mascotaId != null) {
            Mascota mascota = new Mascota();
            mascota.setMascotaId(mascotaId);
            return mascota;
        }
        return null;
    }

    default Long mapHorario(Horario horario) {
        return horario != null ? horario.getId() : null;
    }

    default Horario mapHorario(Long horarioId) {
        if (horarioId != null) {
            Horario horario = new Horario();
            horario.setId(horarioId);
            return horario;
        }
        return null;
    }

    default Long mapVeterinario(Veterinario veterinario) {
        return veterinario != null ? veterinario.getId() : null;
    }

    default Veterinario mapVeterinario(Long veterinarioId) {
        if (veterinarioId != null) {
            Veterinario veterinario = new Veterinario();
            veterinario.setId(veterinarioId);
            return veterinario;
        }
        return null;
    }

    default Long mapTipoCita(TipoCita tipoCita) {
        return tipoCita != null ? tipoCita.getId() : null;
    }

    default TipoCita mapTipoCita(Long tipoCitaId) {
        if (tipoCitaId != null) {
            TipoCita tipoCita = new TipoCita();
            tipoCita.setId(tipoCitaId);
            return tipoCita;
        }
        return null;
    }
}