package com.comfe.tallerElectiva.utils;

import com.comfe.tallerElectiva.dtos.UsuarioDTO;
import com.comfe.tallerElectiva.model.Usuario;
import org.springframework.stereotype.Component;

@Component
public class UsuarioMapper {
    public UsuarioDTO convertirAUsuarioDTO(Usuario usuario) {
        return new UsuarioDTO(usuario.getNombre() + " " + usuario.getApellido());
    }
}

