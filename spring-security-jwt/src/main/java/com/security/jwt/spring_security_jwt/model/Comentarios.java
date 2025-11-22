package com.security.jwt.spring_security_jwt.model;

import jakarta.persistence.*;
import lombok.*;

import java.sql.Date;

@Entity
@Getter @Setter
@Table(name = "comentarios")
public class Comentarios {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idComentario;

    @ManyToOne
    @JoinColumn(name = "idProducto")
    private Producto producto;

    @ManyToOne
    @JoinColumn(name = "idUsuario")
    private Usuario usuario;

    private String comentario;
    private Date fecha;
}