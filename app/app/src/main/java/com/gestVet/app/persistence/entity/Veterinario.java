package com.gestionvet.gestionvet.persistence.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "veterinario")
public class Veterinario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "veterinario_id", nullable = false)
    private Long id;

    @Column(name = "especialidad", nullable = false, length = 200)
    private String especialidad;

    @Column(name = "tarjeta_profesional", nullable = false, length = 30)
    private String tarjetaProfesional;

    @OneToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;

    @OneToMany(mappedBy = "veterinario")
    private List<Cita> citas = new ArrayList<>();

    @OneToMany(mappedBy = "veterinario")
    private List<VeterinarioHorario> veterinarioHorarios = new ArrayList<>();
}