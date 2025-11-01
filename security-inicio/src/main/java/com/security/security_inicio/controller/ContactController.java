package com.security.security_inicio.controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;


@RestController
public class ContactController {

    @GetMapping("/contact")
    public String getContact() {
        return "Este es la informacion de contactos desde la DB";
    }
    
}
