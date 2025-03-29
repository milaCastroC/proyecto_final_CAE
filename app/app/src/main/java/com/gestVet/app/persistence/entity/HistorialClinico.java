package com.gestionvet.gestionvet.persistence.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "historial_clinico")
public class HistorialClinico {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "historial_clinico_id", nullable = false)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "mascota_id")
    private Mascota mascota;

    @OneToMany(mappedBy = "historialClinico")
    private List<ItemHistorial> itemHistorials = new ArrayList<>();
}