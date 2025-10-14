package com.example.demo.controller;

import com.example.demo.model.User;
import com.example.demo.dto.UserResponseDTO;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class UserController {

    @Autowired
    private UserService userService;

  
    @PostMapping("/users")
    public UserResponseDTO create(@RequestBody User user) {
        return userService.createUser(user);
    }

  
    @GetMapping("/users")
    public List<User> getAll() {
        return userService.getAllUsers();
    }

   
    @GetMapping("/users/{id}")
    public ResponseEntity<User> getById(@PathVariable Long id) {
        return userService.getUserById(id)
                          .map(ResponseEntity::ok)
                          .orElse(ResponseEntity.notFound().build());
    }

   
    @GetMapping("/params")
    public Map<String, String> getFullName(@RequestParam String nombre, @RequestParam String apellido) {
        // Formato: {"nombreCompleto": "xxx yyy"}
        String nombreCompleto = nombre + " " + apellido;
        
        Map<String, String> jsonResponse = new HashMap<>();
        jsonResponse.put("nombreCompleto", nombreCompleto);

        return jsonResponse;
    }
}