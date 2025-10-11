package com.example.registros.usuarios;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.annotation.PostConstruct;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;

    @Autowired
    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @PostConstruct
    public void inicializarUsuarios() {
        if (usuarioRepository.count() == 0) {
            usuarioRepository.save(new Usuario(null, "JUAN", "PEREZ", "juan.perez@email.com"));
            usuarioRepository.save(new Usuario(null, "MARIA", "GARCIA", "maria.garcia@email.com"));
            usuarioRepository.save(new Usuario(null, "CARLOS", "RODRIGUEZ", "carlos.rodriguez@email.com"));
            usuarioRepository.save(new Usuario(null, "ANA", "MARTINEZ", "ana.martinez@email.com"));
            usuarioRepository.save(new Usuario(null, "LUIS", "LOPEZ", "luis.lopez@email.com"));
        }
    }

    // 1. Crear usuario y devolver UsuarioDTO (nombre y apellido en mayúsculas)
    public UsuarioDTO crearUsuario(Long id, String nombre, String apellido, String email) {
        Usuario nuevoUsuario = new Usuario(id, nombre.toUpperCase(), apellido.toUpperCase(), email);
        usuarioRepository.save(nuevoUsuario);
        return new UsuarioDTO(nombre.toUpperCase(), apellido.toUpperCase());
    }

    // 2. Obtener todos los usuarios (lista completa)
    public List<Usuario> obtenerTodosUsuarios() {
        return usuarioRepository.findAll();
    }

    // 3. Obtener usuario por id
    public Optional<Usuario> obtenerUsuarioPorId(Long id) {
        return usuarioRepository.findById(id);
    }

    // 4. Crear nombre completo a partir de parámetros nombre y apellido
    public String obtenerNombreCompleto(String nombre, String apellido) {
        return nombre + " " + apellido;
    }

    // 5. Eliminar usuario por id
    public boolean eliminarUsuarioPorId(Long id) {
        if (usuarioRepository.existsById(id)) {
            usuarioRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
