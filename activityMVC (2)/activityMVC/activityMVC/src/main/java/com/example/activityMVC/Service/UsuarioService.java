package com.example.activityMVC.Service;

import com.example.activityMVC.model.Usuario;
import java.util.*;
import java.util.concurrent.atomic.AtomicLong;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {

    private final List<Usuario> usuarios = new ArrayList<>();
    private final AtomicLong counter = new AtomicLong(4);

    public UsuarioService() {
        usuarios.add(new Usuario(1L, "Juan", "Pérez", "juan@test.com"));
        usuarios.add(new Usuario(2L, "María", "García", "maria@test.com"));
        usuarios.add(new Usuario(3L, "Pedro", "López", "pedro@test.com"));
    }

    public Usuario createUsuario(Usuario usuario) {
        usuario.setId(counter.getAndIncrement());
        usuarios.add(usuario);
        return usuario;
    }

    public List<Usuario> getAllUsuarios() {
        return usuarios;
    }

    public Usuario getUsuarioById(Long id) {
        return usuarios.stream().filter(u -> u.getId().equals(id)).findFirst().orElse(null);
    }
}