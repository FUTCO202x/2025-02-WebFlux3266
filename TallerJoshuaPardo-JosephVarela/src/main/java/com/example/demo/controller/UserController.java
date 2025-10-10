package com.example.demo.controller;

import com.example.demo.DTO.UserDTO;
import com.example.demo.model.User;
import com.example.demo.service.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class UserController {

    private final UserService userService;
    public UserController(UserService userService) {
        this.userService = userService;
    }

    // POST: sigue igual
    @PostMapping("/users")
    public User createUser(@RequestBody User user) {
        return userService.addUser(user);
    }

    // ✅ GET: devuelve lista de DTOs
    @GetMapping("/users")
    public List<UserDTO> getAllUsers() {
        return userService.getAllUsers()
                .stream()
                .map(user -> new UserDTO(user.getNombre(), user.getApellido()))
                .collect(Collectors.toList());
    }

    // ✅ GET by ID: devuelve solo un DTO
    @GetMapping("/users/{id}")
    public UserDTO getUserById(@PathVariable int id) {
        User user = userService.getUserById(id);
        return new UserDTO(user.getNombre(), user.getApellido());
    }

    // Ejemplo adicional
    @GetMapping("/params")
    public Map<String, String> getParams(@RequestParam String nombre, @RequestParam String apellido) {
        Map<String, String> response = new HashMap<>();
        response.put("nombreCompleto", nombre.toUpperCase() + " " + apellido.toUpperCase());
        return response;
    }
}
