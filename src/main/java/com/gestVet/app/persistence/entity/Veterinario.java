package com.gestVet.app.persistence.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "veterinario")
@PrimaryKeyJoinColumn(name = "persona_id")
@DiscriminatorValue("VET")
public class Veterinario extends Usuario{

    @Column(name = "especialidad", nullable = false, length = 200)
    private String especialidad;

    @Column(name = "tarjeta_profesional", nullable = false, length = 30)
    private String tarjetaProfesional;

    @OneToMany(mappedBy = "veterinario")
    private List<Cita> citas = new ArrayList<>();

    @OneToMany(mappedBy = "veterinario")
    private List<VeterinarioHorario> veterinarioHorarios = new ArrayList<>();
}