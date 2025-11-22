package com.security.jwt.spring_security_jwt.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.sql.Date;
import java.util.Set;

@Entity
@Getter @Setter
@Table(name = "productos")
public class Producto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idProducto;

    private String nombre;
    private String descripcion;
    private double precio;
    private int stock;

    @ManyToOne
    @JoinColumn(name = "idCategoria")
    private Categoria categoria;

    @OneToMany(mappedBy = "producto", cascade = CascadeType.ALL)
    @JsonIgnore
    private Set<Comentarios> comentarios;
}