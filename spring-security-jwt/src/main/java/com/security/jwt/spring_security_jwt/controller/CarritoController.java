package com.security.jwt.spring_security_jwt.controller;

import com.security.jwt.spring_security_jwt.DTOs.CarritoRequestDTO;
import com.security.jwt.spring_security_jwt.model.CarritoDeCompras;
import com.security.jwt.spring_security_jwt.model.Producto;
import com.security.jwt.spring_security_jwt.model.Usuario;
import com.security.jwt.spring_security_jwt.repository.CarritoDeComprasRepository;
import com.security.jwt.spring_security_jwt.repository.ProductoRepository;
import com.security.jwt.spring_security_jwt.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class CarritoController {

    private final CarritoDeComprasRepository carritoRepository;
    private final UsuarioRepository usuarioRepository;
    private final ProductoRepository productoRepository;

    @PostMapping("/carrito")
    public CarritoDeCompras crearCarrito(@RequestBody CarritoRequestDTO dto, Authentication auth) {
        Usuario usuario = usuarioRepository.findByCorreoElectronico(auth.getName())
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        List<Producto> productos = (List<Producto>) productoRepository.findAllById(dto.productoIds());

        for (Producto p : productos) {
            if (p.getStock() <= 0) {
                throw new RuntimeException("Sin stock para: " + p.getNombre());
            }
            p.setStock(p.getStock() - 1);
        }

        productoRepository.saveAll(productos);

        CarritoDeCompras carrito = new CarritoDeCompras();
        carrito.setUsuario(usuario);
        carrito.setProductos(productos);
        carrito.setSubtotal(dto.subtotal());
        carrito.setImpuestos(dto.impuestos());

        return carritoRepository.save(carrito);
    }

    @GetMapping("/carrito/{id}")
    public CarritoDeCompras obtenerCarrito(@PathVariable int id, Authentication auth) {
        Usuario usuario = usuarioRepository.findByCorreoElectronico(auth.getName())
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        return carritoRepository.findByIdCarritoAndUsuario_IdUsuario(id, usuario.getIdUsuario())
                .orElseThrow(() -> new RuntimeException("Carrito no encontrado o no pertenece al usuario"));
    }
}