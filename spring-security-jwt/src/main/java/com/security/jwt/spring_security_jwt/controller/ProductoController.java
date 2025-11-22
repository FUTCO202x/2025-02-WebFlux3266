package com.security.jwt.spring_security_jwt.controller;

import com.security.jwt.spring_security_jwt.model.Producto;
import com.security.jwt.spring_security_jwt.repository.ProductoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ProductoController {

    private final ProductoRepository productoRepository;

    @GetMapping("/productos")
    public List<Producto> listarProductosPorStock(@RequestParam int stockMax) {
        return productoRepository.findByStockLessThanEqual(stockMax);
    }
}