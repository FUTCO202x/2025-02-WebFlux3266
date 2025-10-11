package com.example.registros.usuarios;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service

public class UsuarioService {

    private List<Usuario> usuarios;

    public UsuarioService() {
        // Crear 3 usuarios por defecto
        usuarios = new ArrayList<>();
        usuarios.add(new Usuario(1L, "JUAN", "PEREZ", "juan.perez@email.com"));
        usuarios.add(new Usuario(2L, "MARIA", "GOMEZ", "maria.gomez@email.com"));
        usuarios.add(new Usuario(3L, "CARLOS", "LOPEZ", "carlos.lopez@email.com"));
    }

    // 1. Crear usuario y devolver UsuarioDTO (nombre y apellido en mayúsculas)
    public UsuarioDTO crearUsuario(Long id, String nombre, String apellido, String email) {
        Usuario nuevoUsuario = new Usuario(id, nombre, apellido, email);
        usuarios.add(nuevoUsuario);
        return new UsuarioDTO(nombre, apellido);
    }

    // 2. Obtener todos los usuarios (lista completa)
    public List<Usuario> obtenerTodosUsuarios() {
        return usuarios;
    }

    // 3. Obtener usuario por id
    public Optional<Usuario> obtenerUsuarioPorId(Long id) {
        return usuarios.stream().filter(u -> u.getId().equals(id)).findFirst();
    }

    // 4. Crear nombre completo a partir de parámetros nombre y apellido
    public String obtenerNombreCompleto(String nombre, String apellido) {
        return nombre + " " + apellido;
    }

    // 5. Eliminar usuario por id
    public boolean eliminarUsuarioPorId(Long id) {
        return usuarios.removeIf(u -> u.getId().equals(id));
    }
}
