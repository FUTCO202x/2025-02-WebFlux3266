package com.comfe.tallerElectiva.controller;

import com.comfe.tallerElectiva.model.Usuario;
import com.comfe.tallerElectiva.dtos.UsuarioDTO;
import com.comfe.tallerElectiva.service.UsuarioService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {

    private final UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @GetMapping
    public List<Usuario> obtenerTodos() {
        return usuarioService.obtenerTodos();
    }

    @PostMapping
    public Usuario crearUsuario(@RequestBody Usuario usuario) {
        return usuarioService.crearUsuario(usuario);
    }

    @GetMapping("/{id}")
    public Usuario obtenerPorId(@PathVariable Long id) {
        return usuarioService.obtenerPorId(id).orElse(null);
    }

    @GetMapping("/buscar")
    public UsuarioDTO buscarPorParams(@RequestParam String nombre,
                                       @RequestParam String apellido) {
        return usuarioService.buscarPorParams(nombre.toLowerCase(), apellido.toLowerCase());
    }

}
