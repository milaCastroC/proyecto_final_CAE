package main.java.com.gestVet.app.persistence.entity;


import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDate;

@Entity
@Data

@Table(name = "factura")
public class Factura {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long facturaId;
    
    private Double total;
    
    private String estado;
    
    private String metodoPago;
    
    private LocalDate fechaEmision;
}