package com.gestionvet.gestionvet.persistence.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;
import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "horario")
public class Horario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "horario_id", nullable = false)
    private Long horarioId;

    @Column(name = "horaInicio", nullable = false)
    private LocalDateTime horaInicio;

    @Column(name = "horaFin", nullable = false)
    private LocalDateTime horaFin;

    @OneToMany(mappedBy = "horario")
    private List<Cita> citas = new ArrayList<>();

    @OneToMany(mappedBy = "horario")
    private List<VeterinarioHorario> veterinarioHorarios = new ArrayList<>();
}