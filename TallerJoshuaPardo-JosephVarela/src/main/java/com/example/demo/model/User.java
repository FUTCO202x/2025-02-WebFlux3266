package com.example.demo.model;

public class User {
    private int id;
    private String nombre;
    private String apellido;
    private String email;

    public User(int id, String nombre, String apellido, String email) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.email = email;
    }

    public int getId() {
        return id;
    }

    public String getNombre() {
        return nombre.toUpperCase();
    }

    public String getApellido() {
        return apellido.toUpperCase();
    }

    public String getEmail() {
        return email;
    }
}
