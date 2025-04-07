package com.gestVet.app.persistence.entity;

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
    private Long itemHistorialId;

    @ManyToOne
    @JoinColumn(name = "mascota_id", nullable = false)
    private Mascota mascota;

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

    @Column(name = "tipo", length = 100)
    private String tipo;
    
    @Column(name = "cita_id")
    private Long citaId;

}