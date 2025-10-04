package com.taller.DTO;

public class UserResponseDTO {
    private String nombre;
    private String apellido;

    public UserResponseDTO(String nombre, String apellido) {
        this.nombre = nombre.toUpperCase();
        this.apellido = apellido.toUpperCase();
    }

    // Getters
    public String getNombre() {
        return nombre;
    }

    public String getApellido() {
        return apellido;
    }
}