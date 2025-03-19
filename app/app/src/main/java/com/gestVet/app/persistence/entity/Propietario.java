package main.java.com.gestVet.app.persistence.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data

@Table(name = "propietario")
public class Propietario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long propietarioId;

    @OneToOne
    @JoinColumn(name = "cliente_id", nullable = false, unique = true)
    private Cliente cliente;
}
