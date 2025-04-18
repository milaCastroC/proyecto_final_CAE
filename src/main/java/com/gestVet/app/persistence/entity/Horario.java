package com.gestVet.app.persistence.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

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
    private LocalTime horaInicio;

    @Column(name = "horaFin", nullable = false)
    private LocalTime horaFin;

    @OneToMany(mappedBy = "horario")
    private List<Cita> citas = new ArrayList<>();

    @OneToMany(mappedBy = "horario")
    private List<VeterinarioHorario> veterinarioHorarios = new ArrayList<>();
}