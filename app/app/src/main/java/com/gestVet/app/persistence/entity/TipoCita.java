package com.gestionvet.gestionvet.persistence.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "tipo_cita")
public class TipoCita {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "tipo_cita_id", nullable = false)
    private Long tipoCitaId;

    @Column(name = "nombre", nullable = false, length = 150)
    private String nombre;

    @OneToMany(mappedBy = "tipoCita")
    private List<Cita> citas = new ArrayList<>();
}