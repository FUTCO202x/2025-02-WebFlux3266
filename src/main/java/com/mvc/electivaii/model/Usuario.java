package com.mvc.electivaii.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Usuario {

    private static int contador = 1;
    private int id;
    private String nombre;
    private String apellido;
    private String email;

    public Usuario(String nombre, String apellido, String email) {
        this.id = contador++;
        this.nombre = nombre;
        this.apellido = apellido;
        this.email = email;
    }

}
