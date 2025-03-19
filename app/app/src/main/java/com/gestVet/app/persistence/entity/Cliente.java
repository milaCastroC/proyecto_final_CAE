package main.java.com.gestVet.app.persistence.entity;


import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data

@Table(name = "cliente")
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long clienteId;

    @OneToOne
    private Persona persona;

    private Boolean esPropietario;

}
