package com.example.demo.DTO;

public class UserDTO {

    private String nombre;
    private String apellido;

    public UserDTO(String nombre, String apellido) {
        this.nombre = nombre.toUpperCase();
        this.apellido = apellido.toUpperCase();
    }

    public String getNombre() {
        return nombre;
    }

    public String getApellido() {
        return apellido;
    }
}
