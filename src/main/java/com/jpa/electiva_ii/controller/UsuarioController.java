package com.jpa.electiva_ii.controller;

import com.jpa.electiva_ii.dto.UsuarioRequestDTO;
import com.jpa.electiva_ii.dto.UsuarioResponseDTO;
import com.jpa.electiva_ii.model.Usuario;
import com.jpa.electiva_ii.service.UsuarioService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping("/crear")
    public ResponseEntity<UsuarioResponseDTO> crearUsuario(@RequestBody UsuarioRequestDTO usuarioRequestDTO) {
        Usuario usuario = new Usuario(usuarioRequestDTO.getNombre(), usuarioRequestDTO.getApellido(), usuarioRequestDTO.getEmail());
        usuarioService.crearUsuario(usuario);

        String nombreMayuscula = usuarioRequestDTO.getNombre() != null ? usuarioRequestDTO.getNombre().toUpperCase() : null;
        String apellidoMayuscula = usuarioRequestDTO.getApellido() != null ? usuarioRequestDTO.getApellido().toUpperCase() : null;

        UsuarioResponseDTO usuarioResponseDTO = new UsuarioResponseDTO(nombreMayuscula, apellidoMayuscula);
        return new ResponseEntity<>(usuarioResponseDTO, HttpStatus.CREATED);
    }

    @GetMapping("/listar")
    public ResponseEntity<List<Usuario>> listarUsuarios() {
        return ResponseEntity.ok(usuarioService.listaUsuarios());
    }

    @GetMapping("/usuario/{id}")
    public ResponseEntity<Usuario> getUserById(@PathVariable("id") Long id) {
        return usuarioService.obtenerUsuario(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/params")
    public ResponseEntity<Map<String, String>> params(@RequestParam("nombre") String nombre,
                                                      @RequestParam("apellido") String apellido) {
        Map<String, String> map = new HashMap<>();
        map.put("nombreCompleto", nombre + " " + apellido);
        return ResponseEntity.ok(map);
    }

}
