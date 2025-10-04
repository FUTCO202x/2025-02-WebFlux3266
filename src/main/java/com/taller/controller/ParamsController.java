package com.taller.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.taller.DTO.NombreCompletoDTO;

@RestController
@RequestMapping("/api/params")
public class ParamsController {

    @GetMapping
    public NombreCompletoDTO getNombreCompleto(
            @RequestParam String nombre,
            @RequestParam String apellido) {
        return new NombreCompletoDTO(nombre, apellido);
    }
}