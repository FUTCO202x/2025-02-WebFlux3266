package com.taller.DTO;

import com.taller.model.User;

public class UserFullDTO {
    private Long id;
    private String nombre;
    private String apellido;
    private String email;

    public UserFullDTO(User user) {
        this.id = user.getId();
        this.nombre = user.getNombre();
        this.apellido = user.getApellido();
        this.email = user.getEmail();
    }

    // Getters
    public Long getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public String getEmail() {
        return email;
    }
}