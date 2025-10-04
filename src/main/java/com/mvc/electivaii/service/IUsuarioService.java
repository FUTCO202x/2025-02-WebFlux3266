package com.mvc.electivaii.service;

import com.mvc.electivaii.model.Usuario;

import java.util.List;
import java.util.Optional;

public interface IUsuarioService {
    Usuario crearUsuario(Usuario usuario);

    List<Usuario> listaUsuarios();

    Optional<Usuario> obtenerUsuario(int id);
}
