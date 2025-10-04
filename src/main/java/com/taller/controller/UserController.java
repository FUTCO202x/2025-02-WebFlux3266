package com.taller.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.taller.DTO.UserFullDTO;
import com.taller.DTO.UserRequestDTO;
import com.taller.DTO.UserResponseDTO;
import com.taller.service.UserService;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }
    

    @PostMapping
    public UserResponseDTO createUser(@RequestBody UserRequestDTO requestDTO) {
        return userService.createUser(requestDTO);
    }

    @GetMapping
    public List<UserFullDTO> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserFullDTO> getUserById(@PathVariable Long id) {
        Optional<UserFullDTO> user = userService.getUserById(id);
        return user.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }
}