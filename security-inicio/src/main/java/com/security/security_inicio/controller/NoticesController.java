package com.security.security_inicio.controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;


@RestController
public class NoticesController {

    @GetMapping("/notices")
    public String getNotices() {
        return "Informacion de noticias de la DB";
    }
    
}
