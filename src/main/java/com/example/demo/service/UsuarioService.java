package com.example.demo.service;

import com.example.demo.model.Usuario;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class UsuarioService {
    private final List<Usuario> usuarios = new ArrayList<>();
    private final AtomicLong seq = new AtomicLong(1);

    public UsuarioService() {
        // 3 usuarios por defecto
        usuarios.add(new Usuario(seq.getAndIncrement(), "Camilo", "Ponton", "camilo@example.com"));
        usuarios.add(new Usuario(seq.getAndIncrement(), "Ana", "Gomez", "ana@example.com"));
        usuarios.add(new Usuario(seq.getAndIncrement(), "Luis", "Martinez", "luis@example.com"));
    }

    public List<Usuario> getAll() {
        return Collections.unmodifiableList(usuarios);
    }

    public Optional<Usuario> getById(Long id) {
        return usuarios.stream().filter(u -> Objects.equals(u.getId(), id)).findFirst();
    }

    public Usuario create(Usuario usuario) {
        usuario.setId(seq.getAndIncrement());
        usuarios.add(usuario);
        return usuario;
    }
}
