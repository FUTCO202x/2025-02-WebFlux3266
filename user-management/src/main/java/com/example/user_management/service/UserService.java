package com.example.user_management.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.stereotype.Service;

import com.example.user_management.dto.UserResponseDTO;
import com.example.user_management.model.User;

@Service
public class UserService {

    private final List<User> users = new ArrayList<>();
    private final AtomicLong counter = new AtomicLong(1);

    public UserService() {
        // usuarios 
        users.add(new User(counter.getAndIncrement(), "Juan", "Pérez", "juan.perez@email.com"));
        users.add(new User(counter.getAndIncrement(), "María", "Gómez", "maria.gomez@email.com"));
        users.add(new User(counter.getAndIncrement(), "Carlos", "López", "carlos.lopez@email.com"));
    }

    public UserResponseDTO createUser(User user) {
        if (user.getId() == null) {
            user.setId(counter.getAndIncrement());
        }

        user.setNombre(user.getNombre().toUpperCase());
        user.setApellido(user.getApellido().toUpperCase());

        users.add(user);

        return new UserResponseDTO(user.getId(), user.getNombre(), user.getApellido());
    }

    public List<User> getAllUsers() {
        return new ArrayList<>(users);
    }

    public Optional<User> getUserById(Long id) {
        return users.stream()
                .filter(user -> user.getId().equals(id))
                .findFirst();
    }

    public void printAllUsers() {
        System.out.println("=== USUARIOS EN MEMORIA ===");
        users.forEach(System.out::println);
        System.out.println("===========================");
    }
}
