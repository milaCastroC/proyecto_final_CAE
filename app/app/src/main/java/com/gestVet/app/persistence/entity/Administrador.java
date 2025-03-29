package com.gestionvet.gestionvet.persistence.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "administrador")
public class Administrador {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "administrador_id", nullable = false)
    private Long id;

    @OneToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;

    @ManyToOne
    @JoinColumn(name = "privilegio_id")
    private Privilegio privilegio;
}