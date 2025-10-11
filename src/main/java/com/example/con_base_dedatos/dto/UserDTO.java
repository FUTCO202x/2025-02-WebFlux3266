package com.example.con_base_dedatos.dto;



public class UserDTO {

    private String nombre;
    private String apellido;

    public UserDTO() {}

    public UserDTO(String nombre, String apellido) {
        this.nombre = nombre != null ? nombre.toUpperCase() : null;
        this.apellido = apellido != null ? apellido.toUpperCase() : null;
    }

    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre != null ? nombre.toUpperCase() : null;
    }

    public String getApellido() {
        return apellido;
    }
    public void setApellido(String apellido) {
        this.apellido = apellido != null ? apellido.toUpperCase() : null;
    }
}
