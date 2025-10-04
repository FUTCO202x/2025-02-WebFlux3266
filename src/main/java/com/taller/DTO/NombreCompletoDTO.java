package com.taller.DTO;

public class NombreCompletoDTO {
    private String nombreCompleto;

    public NombreCompletoDTO(String nombre, String apellido) {
        this.nombreCompleto = nombre + " " + apellido;
    }

    public String getNombreCompleto() {
        return nombreCompleto;
    }
}