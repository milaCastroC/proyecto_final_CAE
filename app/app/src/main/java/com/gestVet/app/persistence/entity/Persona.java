package main.java.com.gestVet.app.persistence.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity


@Table(name = "persona")
public class Persona {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "persona_id")
    private Long id;

    @OneToOne
    @JoinColumn(name = "identificacion", referencedColumnName = "numero_identificacion", unique = true)
    private Identificacion identificacion;

    private String nombre;

    private String apellido;

    private String telefono;

    private String email;

    private String direccion;
}

