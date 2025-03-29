package com.gestionvet.gestionvet.persistence.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "persona")
public class Persona {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "persona_id", nullable = false)
    private Long id;

    @OneToOne
    @JoinColumn(name = "identificacion_id")
    private Identificacion identificacion;

    @Column(name = "nombre", nullable = false, length = 100)
    private String nombre;

    @Column(name = "apellido", nullable = false, length = 100)
    private String apellido;

    @Column(name = "telefono", length = 20)
    private String telefono;

    @Column(name = "email", length = 100)
    private String email;

    @Column(name = "direccion")
    private String direccion;

    @OneToOne(mappedBy = "persona")
    private Cliente cliente;

    @OneToOne(mappedBy = "persona")
    private Usuario usuario;
}