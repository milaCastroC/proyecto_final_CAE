package main.java.com.gestVet.app.persistence.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "mascota")
public class Mascota {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "mascota_id")
    private Long mascotaId;

    @ManyToOne
    @JoinColumn(name = "propietario_id", nullable = false)
    private Propietario propietario;

    private String nombre;

    private String especie;

    private String raza;

    @Enumerated(EnumType.STRING)
    private Sexo sexo;

    private LocalDateTime fechaNacimiento;

    private Integer edad;

    private Double peso;
    
    @OneToOne(mappedBy = "mascota", cascade = CascadeType.ALL)
    private HistorialClinico historialClinico;
    
    public enum Sexo {
        MACHO, HEMBRA
    }
}
