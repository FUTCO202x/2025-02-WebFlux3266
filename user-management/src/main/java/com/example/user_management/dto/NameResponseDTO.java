package com.example.user_management.dto;

public class NameResponseDTO {
    private String nombreCompleto;

    public NameResponseDTO(String nombre, String apellido) {
        this.nombreCompleto = nombre + " " + apellido;
    }

    public String getNombreCompleto() {
        return nombreCompleto;
    }

    public void setNombreCompleto(String nombreCompleto) {
        this.nombreCompleto = nombreCompleto;
    }
}

