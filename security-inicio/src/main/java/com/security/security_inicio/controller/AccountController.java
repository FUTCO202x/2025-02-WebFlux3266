package com.security.security_inicio.controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;


@RestController
public class AccountController {

    @GetMapping("/myAccount")
    public String getAccount() {
        return "Informacion de cuentas desde la DB";
    }
    
}
