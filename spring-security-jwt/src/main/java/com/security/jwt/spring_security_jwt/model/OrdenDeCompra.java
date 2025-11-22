package com.security.jwt.spring_security_jwt.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Getter @Setter
@Table(name = "orden_de_compra")
public class OrdenDeCompra {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idOrden;

    @ManyToMany
    @JoinTable(
        name = "orden_productos",
        joinColumns = @JoinColumn(name = "idOrden"),
        inverseJoinColumns = @JoinColumn(name = "idProducto")
    )
    private List<Producto> productos;

    private double subtotal;
    private double impuestos;
    private double envio;
    private double total;
}