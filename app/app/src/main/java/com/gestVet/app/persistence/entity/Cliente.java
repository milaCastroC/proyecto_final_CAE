package com.gestionvet.gestionvet.persistence.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "cliente")
public class Cliente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cliente_id", nullable = false)
    private Long id;

    @OneToOne
    @JoinColumn(name = "persona_id")
    private Persona persona;

    @Column(name = "es_propietario", nullable = false)
    private Boolean esPropietario = false;

    @OneToMany(mappedBy = "cliente")
    private List<ClienteFactura> clienteFacturas = new ArrayList<>();

    @OneToOne(mappedBy = "cliente")
    private Propietario propietario;
}