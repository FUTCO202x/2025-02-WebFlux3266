package com.example.activityMVC.controller;

import com.example.activityMVC.dto.UsuarioDTO;
import com.example.activityMVC.model.Usuario;
import com.example.activityMVC.Service.UsuarioService;
import org.springframework.web.bind.annotation.*;
import java.util.*;

@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {

    private final UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    // 1. Crear usuario
    @PostMapping
    public UsuarioDTO createUsuario(@RequestBody Usuario usuario) {
        Usuario nuevo = usuarioService.createUsuario(usuario);
        return new UsuarioDTO(nuevo.getNombre(), nuevo.getApellido());
    }

    // 2. Obtener todos los usuarios
    @GetMapping
    public List<Usuario> getAllUsuarios() {
        return usuarioService.getAllUsuarios();
    }

    // 3. Obtener usuario por id
    @GetMapping("/{id}")
    public Usuario getUsuarioById(@PathVariable Long id) {
        return usuarioService.getUsuarioById(id);
    }

    // 4. Parámetros en la URL
    @GetMapping("/params")
    public Map<String, String> getNombreCompleto(@RequestParam String nombre, @RequestParam String apellido) {
        Map<String, String> response = new HashMap<>();
        response.put("nombreCompleto", nombre + " " + apellido);
        return response;
    }
}