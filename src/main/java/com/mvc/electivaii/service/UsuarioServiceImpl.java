package com.mvc.electivaii.service;

import com.mvc.electivaii.model.Usuario;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UsuarioServiceImpl implements IUsuarioService {
    private final List<Usuario> usuarios = new ArrayList<>();

    public UsuarioServiceImpl() {
        usuarios.add(new Usuario("Goku", "Son", "kamehameha@gmail.com"));
        usuarios.add(new Usuario("Naruto", "Uzumaki", "dattebayo@gmail.com"));
        usuarios.add(new Usuario("Adolf", "Hitler", "laterceraeslavencida@gmail.com"));
    }

    @Override
    public Usuario crearUsuario(Usuario usuario) {
        usuarios.add(usuario);
        return usuario;
    }

    @Override
    public List<Usuario> listaUsuarios() {
        return usuarios;
    }

    @Override
    public Optional<Usuario> obtenerUsuario(int id) {
        return usuarios.stream()
                .filter(usuario -> usuario.getId() == id)
                .findFirst();
    }

}
