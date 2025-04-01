package com.gestVet.app.persistence.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "cita")
public class Cita {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cita_id", nullable = false)
    private Long citaId;

    @ManyToOne
    @JoinColumn(name = "mascota_id")
    private Mascota mascota;

    @ManyToOne
    @JoinColumn(name = "horario_id")
    private Horario horario;

    @ManyToOne
    @JoinColumn(name = "veterinario_id")
    private Veterinario veterinario;

    @Column(name = "tipo_cita", nullable = false)
    private String tipoCita;

    @Column(name = "fecha", nullable = false)
    private LocalDateTime fecha;

    @Column(name = "estado", nullable = false)
    private String estado;
}