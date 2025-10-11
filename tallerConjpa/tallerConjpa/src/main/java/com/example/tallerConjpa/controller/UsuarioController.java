package com.example.tallerConjpa.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.tallerConjpa.model.Usuario;
import com.example.tallerConjpa.repository.UsuarioRepository;

@RestController
@RequestMapping("/api")
public class UsuarioController {

    @Autowired
    private UsuarioRepository usuarioRepository;

   
    @PostMapping("/usuario")
    public Map<String, String> crearUsuario(@RequestBody Usuario usuario) {
        usuarioRepository.save(usuario);
        Map<String, String> response = new HashMap<>();
        response.put("nombre", usuario.getNombre().toUpperCase());
        response.put("apellido", usuario.getApellido().toUpperCase());
        return response;
    }


    @GetMapping("/usuarios")
    public List<Usuario> obtenerTodos() {
        return usuarioRepository.findAll();
    }

   
    @GetMapping("/usuario/{id}")
    public Usuario obtenerPorId(@PathVariable Long id) {
        return usuarioRepository.findById(id).orElse(null);
    }

   
    @GetMapping("/params")
    public Map<String, String> nombreCompleto(@RequestParam String nombre,
                                              @RequestParam String apellido) {
        Map<String, String> response = new HashMap<>();
        response.put("nombreCompleto", nombre + " " + apellido);
        return response;
    }
}