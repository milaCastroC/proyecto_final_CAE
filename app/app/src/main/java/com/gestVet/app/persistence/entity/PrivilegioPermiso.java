package com.gestVet.app.persistence.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "privilegio_permiso")
public class PrivilegioPermiso {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "privilegio_permiso_id", nullable = false)
    private Long privilegioPermisoId;

    @ManyToOne
    @JoinColumn(name = "privilegio_id")
    private Privilegio privilegio;

    @ManyToOne
    @JoinColumn(name = "permiso_id")
    private Permiso permiso;
}