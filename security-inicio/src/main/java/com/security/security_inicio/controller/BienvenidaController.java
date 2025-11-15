package com.security.security_inicio.controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;


@RestController
public class BienvenidaController {

    @GetMapping("/bienvenida")
    public String bienvenida() {
        return "Bienvenido a Spring Security";
    }
    
}
