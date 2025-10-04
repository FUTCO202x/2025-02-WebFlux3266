package com.comfe.tallerElectiva.service;

import com.comfe.tallerElectiva.model.Usuario;
import com.comfe.tallerElectiva.dtos.UsuarioDTO;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.comfe.tallerElectiva.utils.Normalize.normalizar;

@Service
public class UsuarioService {

    private final List<Usuario> usuarios = new ArrayList<>();

    public UsuarioService() {
        usuarios.add(new Usuario(1L, "Ana", "García", "ana@hotmail.com"));
        usuarios.add(new Usuario(2L, "Luis", "Martínez", "luis@gmail.com"));
        usuarios.add(new Usuario(3L, "Sofía", "López", "sofia@outlook.com"));
    }

    public List<Usuario> obtenerTodos() {
        return usuarios;
    }

    public Optional<Usuario> obtenerPorId(Long id) {
        return usuarios.stream().filter(u -> u.getId().equals(id)).findFirst();
    }

    public Usuario crearUsuario(Usuario usuario) {
        usuarios.add(usuario);
        return usuario;
    }

    public UsuarioDTO buscarPorParams(String nombre, String apellido) {
        String nombreNorm = normalizar(nombre);
        String apellidoNorm = normalizar(apellido);
        Usuario usuario = usuarios.stream()
                .filter(u -> normalizar(u.getNombre()).equals(nombre) &&
                        normalizar(u.getApellido()).equals(apellido))
                .findFirst()
                .orElse(null);

        if (usuario != null) {
            return obtenerNombreCompleto(usuario.getNombre(), usuario.getApellido());
        } else {
            return null;
        }
    }

    public UsuarioDTO obtenerNombreCompleto(String nombre, String apellido) {
        return new UsuarioDTO(nombre + " " + apellido);
    }
}

