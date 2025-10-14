package com.example.demo.dto;

public class UserResponseDTO {
    private String nombre;
    private String apellido;

    // Constructor
    public UserResponseDTO(String nombre, String apellido) {
        this.nombre = nombre;
        this.apellido = apellido;
    }

    // Getters
    public String getNombre() {
        return nombre;
    }

    public String getApellido() {
        return apellido;
    }

}