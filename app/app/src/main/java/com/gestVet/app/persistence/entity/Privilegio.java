package com.gestVet.app.persistence.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "privilegio")
public class Privilegio {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "privilegio_id", nullable = false)
    private Long privilegioId;

    @Column(name = "nombre", nullable = false, length = 200)
    private String nombre;

    @OneToMany(mappedBy = "privilegio")
    private List<Administrador> administradores = new ArrayList<>();

    @OneToMany(mappedBy = "privilegio")
    private List<PrivilegioPermiso> privilegioPermisos = new ArrayList<>();
}