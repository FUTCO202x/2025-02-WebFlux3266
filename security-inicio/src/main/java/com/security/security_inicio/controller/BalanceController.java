package com.security.security_inicio.controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;


@RestController
public class BalanceController {

    @GetMapping("/myBalance")
    public String getMethodName() {
        return "Informacion de balance de cuentas desde la DB";
    }
    
}
