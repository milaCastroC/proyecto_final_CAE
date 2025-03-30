package com.gestionvet.gestionvet.persistence.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "identificacion")
public class Identificacion {
    @Id
    @Column(name = "numero_identificacion", nullable = false)
    private Long identificacion;

    @Column(name = "tipo", nullable = false, length = 50)
    private String tipo;

    @Column(name = "estado", nullable = false)
    private Boolean estado = false;

    @OneToOne(mappedBy = "identificacion")
    private Persona persona;
}