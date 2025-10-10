package com.example.tallerSinjpa.controller;

import com.example.tallerSinjpa.model.Usuario;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/api")
public class UsuarioController {

    private List<Usuario> usuarios = new ArrayList<>();

    public UsuarioController() {
        usuarios.add(new Usuario(1L, "Daniel", "Munoz", "daniel@email.com"));
        usuarios.add(new Usuario(2L, "Kathe", "Correa", "kathe@email.com"));
       
    }

    @PostMapping("/usuario")
    public Map<String, String> crearUsuario(@RequestBody Usuario usuario) {
        usuarios.add(usuario);
        Map<String, String> response = new HashMap<>();
        response.put("nombre", usuario.getNombre().toUpperCase());
        response.put("apellido", usuario.getApellido().toUpperCase());
        return response;
    }

    @GetMapping("/usuarios")
    public List<Usuario> obtenerTodos() {
        return usuarios;
    }

    @GetMapping("/usuario/{id}")
    public Usuario obtenerPorId(@PathVariable Long id) {
        return usuarios.stream()
                .filter(u -> u.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    @GetMapping("/params")
    public Map<String, String> nombreCompleto(@RequestParam String nombre,
                                              @RequestParam String apellido) {
        Map<String, String> response = new HashMap<>();
        response.put("nombreCompleto", nombre + " " + apellido);
        return response;
    }
}