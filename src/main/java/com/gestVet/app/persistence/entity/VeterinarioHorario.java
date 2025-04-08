package com.gestVet.app.persistence.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "veterinario_horario")
public class VeterinarioHorario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "veterinario_horario_id", nullable = false)
    private Long veterinarioHorarioId;

    @Column(name = "dia_semana", nullable = false)
    private int diaSemana;

    @ManyToOne
    @JoinColumn(name = "veterinario_id")
    private Veterinario veterinario;

    @ManyToOne
    @JoinColumn(name = "horario_id")
    private Horario horario;
}