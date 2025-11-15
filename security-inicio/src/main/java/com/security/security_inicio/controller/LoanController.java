package com.security.security_inicio.controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;


@RestController
public class LoanController {

    @GetMapping("/myLoans")
    public String getLoans() {
        return "Informacion de prestamos desde la DB";
    }
    
}
