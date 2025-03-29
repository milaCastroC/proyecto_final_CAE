package com.gestionvet.gestionvet.persistence.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "privilegio")
public class Privilegio {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "privilegio_id", nullable = false)
    private Long id;

    @Column(name = "nombre", nullable = false, length = 200)
    private String nombre;

    @OneToMany(mappedBy = "privilegio")
    private List<Administrador> administradores = new ArrayList<>();

    @OneToMany(mappedBy = "privilegio")
    private List<PrivilegioPermiso> privilegioPermisos = new ArrayList<>();
}