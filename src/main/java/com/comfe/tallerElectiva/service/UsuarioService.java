package com.comfe.tallerElectiva.service;

import com.comfe.tallerElectiva.model.Usuario;
import com.comfe.tallerElectiva.dtos.UsuarioDTO;
import com.comfe.tallerElectiva.repository.UsuarioRepository;
import com.comfe.tallerElectiva.utils.Normalize;
import com.comfe.tallerElectiva.utils.UsuarioMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final UsuarioMapper usuarioMapper;

    public UsuarioService(UsuarioRepository usuarioRepository, UsuarioMapper usuarioMapper) {
        this.usuarioRepository = usuarioRepository;
        this.usuarioMapper = usuarioMapper;
    }

    // Obtener todos los usuarios
    public List<Usuario> obtenerTodos() {
        return usuarioRepository.findAll();
    }

    // Obtener usuario por ID
    public Optional<Usuario> obtenerPorId(Long id) {
        return usuarioRepository.findById(id);
    }

    // Crear nuevo usuario
    public Usuario crearUsuario(Usuario usuario) {
        return usuarioRepository.save(usuario);
    }

    // Buscar por nombre y apellido normalizados
    public UsuarioDTO buscarPorParams(String nombre, String apellido) {
        String nombreNorm = Normalize.normalizar(nombre);
        String apellidoNorm = Normalize.normalizar(apellido);

        Optional<Usuario> usuarioOpt = usuarioRepository.findAll().stream()
                .filter(u -> Normalize.normalizar(u.getNombre()).equals(nombreNorm) &&
                        Normalize.normalizar(u.getApellido()).equals(apellidoNorm))
                .findFirst();

        return usuarioOpt.map(usuarioMapper::convertirAUsuarioDTO).orElse(null);
    }

    // Construir DTO con nombre completo
    public UsuarioDTO obtenerNombreCompleto(String nombre, String apellido) {
        return new UsuarioDTO(nombre + " " + apellido);
    }
}

