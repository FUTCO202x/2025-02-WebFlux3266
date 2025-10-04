package com.mvc.electivaii.controller;

import com.mvc.electivaii.dto.UsuarioRequestDTO;
import com.mvc.electivaii.dto.UsuarioResponseDTO;
import com.mvc.electivaii.model.Usuario;
import com.mvc.electivaii.service.UsuarioServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class UsuarioController {

    private final UsuarioServiceImpl usuarioServiceImpl;

    public UsuarioController(UsuarioServiceImpl usuarioServiceImpl) {
        this.usuarioServiceImpl = usuarioServiceImpl;
    }

    @PostMapping("/crear")
    public ResponseEntity<UsuarioResponseDTO> crearUsuario(@RequestBody UsuarioRequestDTO usuarioRequestDTO) {
        Usuario usuario = new Usuario(usuarioRequestDTO.getNombre(), usuarioRequestDTO.getApellido(), usuarioRequestDTO.getEmail());
        usuarioServiceImpl.crearUsuario(usuario);

        String nombreMayuscula = usuarioRequestDTO.getNombre() != null ? usuarioRequestDTO.getNombre().toUpperCase() : null;
        String apellidoMayuscula = usuarioRequestDTO.getApellido() != null ? usuarioRequestDTO.getApellido().toUpperCase() : null;

        UsuarioResponseDTO usuarioResponseDTO = new UsuarioResponseDTO(nombreMayuscula, apellidoMayuscula);
        return new ResponseEntity<>(usuarioResponseDTO, HttpStatus.CREATED);
    }

    @GetMapping("/listar")
    public ResponseEntity<List<Usuario>> listarUsuarios() {
        return ResponseEntity.ok(usuarioServiceImpl.listaUsuarios());
    }

    @GetMapping("/usuario/{id}")
    public ResponseEntity<Usuario> getUserById(@PathVariable("id") int id) {
        return usuarioServiceImpl.obtenerUsuario(id)
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
