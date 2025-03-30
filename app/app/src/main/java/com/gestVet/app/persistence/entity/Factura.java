package com.gestVet.app.persistence.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "factura")
public class Factura {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "factura_id", nullable = false)
    private Long facturaId;

    @Column(name = "total", nullable = false)
    private Double total;

    @Column(name = "estado", nullable = false, length = 50)
    private String estado;

    @Column(name = "metodo_pago", nullable = false, length = 60)
    private String metodoPago;

    @Column(name = "fecha_emision", nullable = false)
    private LocalDate fechaEmision;

    @OneToMany(mappedBy = "factura")
    private List<ClienteFactura> clienteFacturas = new ArrayList<>();
}