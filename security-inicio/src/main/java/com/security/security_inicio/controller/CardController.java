package com.security.security_inicio.controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;


@RestController
public class CardController {

    @GetMapping("/myCards")
    public String getCards() {
        return "Informacion de tarjetas desde la DB";
    }
    
}
