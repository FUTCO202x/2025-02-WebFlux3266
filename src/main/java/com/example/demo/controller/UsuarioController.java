package com.example.demo.controller;

import com.example.demo.dto.UsuarioResponse;
import com.example.demo.model.Usuario;
import com.example.demo.service.UsuarioService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class UsuarioController {

    private final UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    // 1. Crear usuario (retorna nombre y apellido en mayúsculas)
    @PostMapping("/usuario")
    public ResponseEntity<UsuarioResponse> createUsuario(@Valid @RequestBody Usuario usuario) {
        Usuario created = usuarioService.create(usuario);
        UsuarioResponse response = new UsuarioResponse(
                created.getNombre() != null ? created.getNombre().toUpperCase() : null,
                created.getApellido() != null ? created.getApellido().toUpperCase() : null
        );
        return ResponseEntity.ok(response);
    }

    // 2. Obtener todos los usuarios (lista con 3 por defecto)
    @GetMapping("/usuarios")
    public ResponseEntity<List<Usuario>> getAll() {
        return ResponseEntity.ok(usuarioService.getAll());
    }

    // 3. Obtener usuario por id
    @GetMapping("/usuario/{id}")
    public ResponseEntity<Usuario> getById(@PathVariable Long id) {
        return usuarioService.getById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // 4. Petición con parámetros en la url
    @GetMapping("/params")
    public ResponseEntity<Map<String, String>> params(@RequestParam String nombre, @RequestParam String apellido) {
        Map<String, String> res = new HashMap<>();
        res.put("nombreCompleto", nombre + " " + apellido);
        return ResponseEntity.ok(res);
    }
}
