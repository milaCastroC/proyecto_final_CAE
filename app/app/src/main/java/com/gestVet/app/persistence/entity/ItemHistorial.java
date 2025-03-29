package com.gestionvet.gestionvet.persistence.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@Entity
@Table(name = "item_historial")
public class ItemHistorial {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "item_historial_id", nullable = false)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "historial_clinico_id")
    private HistorialClinico historialClinico;

    @Column(name = "fecha", nullable = false)
    private LocalDate fecha;

    @Lob
    @Column(name = "diagnostico", nullable = false)
    private String diagnostico;

    @Lob
    @Column(name = "tratamiento", nullable = false)
    private String tratamiento;

    @Lob
    @Column(name = "observaciones")
    private String observaciones;

    @Column(name = "tipos", length = 100)
    private String tipos;
}