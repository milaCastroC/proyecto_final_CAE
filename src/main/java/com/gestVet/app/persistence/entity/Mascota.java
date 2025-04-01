package com.gestVet.app.persistence.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "mascota")
public class Mascota {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "mascota_id", nullable = false)
    private Long mascotaId;

    @ManyToOne
    @JoinColumn(name = "propietario_id")
    private Cliente propietario;

    @Column(name = "nombre", nullable = false, length = 100)
    private String nombre;

    @Column(name = "especie", nullable = false, length = 150)
    private String especie;

    @Column(name = "raza", length = 150)
    private String raza;

    @Column(name = "sexo", nullable = false)
    private String sexo;

    @Column(name = "fecha_nacimiento")
    private LocalDate fechaNacimiento;

    @Column(name = "edad")
    private Integer edad;

    @Column(name = "peso", precision = 5, scale = 2)
    private BigDecimal peso;

    @OneToMany(mappedBy = "mascota")
    private List<Cita> citas = new ArrayList<>();

    @OneToMany(mappedBy = "mascota")
    private List<ItemHistorial> historialClinico = new ArrayList<>();
}