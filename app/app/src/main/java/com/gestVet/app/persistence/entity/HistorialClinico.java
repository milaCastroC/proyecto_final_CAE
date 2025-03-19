package main.java.com.gestVet.app.persistence.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.util.List;

@Entity
@Data

@Table(name = "historial_clinico")
public class HistorialClinico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "historial_clinico_id")
    private Long historialClinicoId;

    @OneToOne
    @JoinColumn(name = "mascota_id", nullable = false)
    private Mascota mascota;

    @OneToMany(mappedBy = "historialClinico", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ItemHistorial> itemsHistorial;
}
