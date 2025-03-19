package main.java.com.gestVet.app.persistence.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity


@Table(name = "identificacion")
public class Identificacion {

    @Id
    @Column(name = "numero_identificacion")
    private Long numeroIdentificacion;

    private String tipo;

    private Boolean estado;
}