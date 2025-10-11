package com.example.registros.usuarios;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {

    private final UsuarioService usuarioService;

    @Autowired
    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    // 1. Crear usuario y retornar nombre y apellido en mayúsculas
    @PostMapping("/crear")
    public UsuarioDTO crearUsuario(@RequestParam Long id,
                                   @RequestParam String nombre,
                                   @RequestParam String apellido,
                                   @RequestParam String email) {
        return usuarioService.crearUsuario(id, nombre, apellido, email);
    }

    // 2. Obtener todos los usuarios
    @GetMapping
    public List<Usuario> obtenerTodos() {
        return usuarioService.obtenerTodosUsuarios();
    }

    // 3. Obtener usuario por id
    @GetMapping("/{id}")
    public Optional<Usuario> obtenerPorId(@PathVariable Long id) {
        return usuarioService.obtenerUsuarioPorId(id);
    }

    // 4. Petición con parámetros en URL
    // http://localhost:9000/api/usuarios/params?nombre=xxx&apellido=yyy
    @GetMapping("/params")
    public NombreCompletoResponse obtenerNombreCompleto(
            @RequestParam String nombre,
            @RequestParam String apellido) {
        String nombreCompleto = usuarioService.obtenerNombreCompleto(nombre, apellido);
        return new NombreCompletoResponse(nombreCompleto);
    }

    // 5. Eliminar usuario por id
    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminarUsuario(@PathVariable Long id) {
        boolean eliminado = usuarioService.eliminarUsuarioPorId(id);
        if (eliminado) {
            return ResponseEntity.ok("Usuario eliminado correctamente.");
        } else {
            return ResponseEntity.status(404).body("Usuario no encontrado.");
        }
    }

    // Clase interna para responder JSON con "nombreCompleto"
    public static class NombreCompletoResponse {
        private String nombreCompleto;

        public NombreCompletoResponse(String nombreCompleto) {
            this.nombreCompleto = nombreCompleto;
        }

        public String getNombreCompleto() {
            return nombreCompleto;
        }
    }
}
