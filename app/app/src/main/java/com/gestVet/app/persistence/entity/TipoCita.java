package main.java.com.gestVet.app.persistence.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data

@Table(name = "tipo_cita")
public class TipoCita {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "tipo_cita_id")
    private Long tipoCitaId;

    private String nombre;
}
