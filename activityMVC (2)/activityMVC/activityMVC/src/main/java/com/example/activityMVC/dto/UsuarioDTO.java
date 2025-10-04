package com.example.activityMVC.dto;

public class UsuarioDTO {
    private String nombre;
    private String apellido;

    public UsuarioDTO(String nombre, String apellido) {
        this.nombre = nombre.toUpperCase();
        this.apellido = apellido.toUpperCase();
    }

    public String getNombre() { return nombre; }
    public String getApellido() { return apellido; }
}