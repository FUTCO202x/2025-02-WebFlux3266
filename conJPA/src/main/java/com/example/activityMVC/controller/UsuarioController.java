package com.example.activityMVC.controller;

import com.example.activityMVC.dto.ApiResponse;
import com.example.activityMVC.model.Usuario;
import com.example.activityMVC.service.UsuarioService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {

    private final UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @PostMapping
    public ResponseEntity<ApiResponse<Usuario>> createUsuario(@RequestBody Usuario usuario) {
        Usuario nuevo = usuarioService.createUsuario(usuario);
        ApiResponse<Usuario> resp = new ApiResponse<>(HttpStatus.CREATED.value(), "Usuario creado exitosamente", nuevo);
        return ResponseEntity.status(HttpStatus.CREATED).body(resp);
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<Usuario>>> getAllUsuarios() {
        List<Usuario> usuarios = usuarioService.getAllUsuarios();
        ApiResponse<List<Usuario>> resp = new ApiResponse<>(HttpStatus.OK.value(), "Usuarios obtenidos correctamente", usuarios);
        return ResponseEntity.ok(resp);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<Usuario>> getUsuarioById(@PathVariable Long id) {
        Usuario usuario = usuarioService.getUsuarioById(id);
        if (usuario != null) {
            ApiResponse<Usuario> resp = new ApiResponse<>(HttpStatus.OK.value(), "Usuario encontrado", usuario);
            return ResponseEntity.ok(resp);
        } else {
            ApiResponse<Usuario> resp = new ApiResponse<>(HttpStatus.NOT_FOUND.value(), "Usuario no encontrado", null);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(resp);
        }
    }

    @GetMapping("/params")
    public ResponseEntity<ApiResponse<Map<String, String>>> getNombreCompleto(@RequestParam String nombre, @RequestParam String apellido) {
        Map<String, String> result = new HashMap<>();
        result.put("nombreCompleto", nombre + " " + apellido);
        ApiResponse<Map<String, String>> resp = new ApiResponse<>(HttpStatus.OK.value(), "Parámetros recibidos correctamente", result);
        return ResponseEntity.ok(resp);
    }
}
