package com.parcial.dos.parcialdos.account.controller;

import com.parcial.dos.parcialdos.account.dto.*;
import com.parcial.dos.parcialdos.account.service.IAccountService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/accounts")
public class AccountController {

    private final IAccountService service;

    public AccountController(IAccountService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<AccountResponseDTO> create(@RequestBody AccountRequestDTO request) {
        AccountResponseDTO created = service.createAccount(request);
        return new ResponseEntity<>(created, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<AccountResponseDTO>> all() {
        return ResponseEntity.ok(service.getAllAccounts());
    }

    @GetMapping("/{id}")
    public ResponseEntity<AccountResponseDTO> getById(@PathVariable Long id) {
        return service.getAccountById(id)
                .map(ResponseEntity::ok)
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateBalance(@PathVariable Long id, @RequestBody AccountRequestDTO request) {
        String result = service.updateBalance(id, request);
        if ("Cuenta no encontrada".equals(result)) {
            return new ResponseEntity<>(result, HttpStatus.NOT_FOUND); // o 200 con mensaje, según prefieras
        }
        return ResponseEntity.ok(result);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.deleteAccount(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/by-number/{numeroCuenta}")
    public ResponseEntity<AccountOwnerBalanceDTO> byNumber(@PathVariable String numeroCuenta) {
        return service.findByAccountNumber(numeroCuenta)
                .map(ResponseEntity::ok)
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
}
