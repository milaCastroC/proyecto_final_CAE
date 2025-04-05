package com.gestVet.app.persistence.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "administrador")
@PrimaryKeyJoinColumn(name = "persona_id") // PK heredada de Usuario/Persona
public class Administrador extends Usuario{

    @Column(name = "cargo")
    private String cargo;

    @Column(name = "area")
    private String area;

}
