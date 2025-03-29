package com.gestionvet.gestionvet.persistence.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

@Getter
@Setter
@Entity
@Table(name = "cita")
public class Cita {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cita_id", nullable = false)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "mascota_id")
    private Mascota mascota;

    @ManyToOne
    @JoinColumn(name = "horario_id")
    private Horario horario;

    @ManyToOne
    @JoinColumn(name = "veterinario_id")
    private Veterinario veterinario;

    @Column(name = "fecha", nullable = false)
    private LocalDateTime fecha;

    @ManyToOne
    @JoinColumn(name = "tipo_cita_id")
    private TipoCita tipoCita;

    @Column(name = "estado", nullable = false)
    private String estado;
}