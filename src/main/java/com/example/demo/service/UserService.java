package com.example.demo.service;

import com.example.demo.model.User;
import com.example.demo.dto.UserResponseDTO;
import com.example.demo.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserResponseDTO createUser(User newUser) {
        userRepository.save(newUser);
        String nombreUpper = newUser.getNombre() != null ? newUser.getNombre().toUpperCase() : "";
        String apellidoUpper = newUser.getApellido() != null ? newUser.getApellido().toUpperCase() : "";
        return new UserResponseDTO(nombreUpper, apellidoUpper);
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public Optional<User> getUserById(Long id) {
        return userRepository.findById(id);
    }
}
