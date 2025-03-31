package com.gestVet.app.persistence.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "usuario")
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "usuario_id", nullable = false)
    private Long usuarioId;

    @Column(name = "username", nullable = false, length = 50)
    private String username;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "rol", nullable = false, length = 20)
    private String rol;

    @OneToOne
    @JoinColumn(name = "persona_id")
    private Persona persona;

    @OneToOne(mappedBy = "usuario")
    private Administrador administrador;

    @OneToOne(mappedBy = "usuario")
    private Veterinario veterinario;
}