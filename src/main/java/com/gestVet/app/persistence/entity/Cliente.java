package com.gestVet.app.persistence.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "cliente")
@PrimaryKeyJoinColumn(name = "persona_id")
public class Cliente extends Persona{

    @Column(name = "es_propietario", nullable = false)
    private Boolean esPropietario = false;

    @OneToMany(mappedBy = "cliente")
    private List<ClienteFactura> clienteFacturas = new ArrayList<>();

}