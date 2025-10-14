package com.example.demo.service;

import com.example.demo.model.User;
import com.example.demo.dto.UserResponseDTO;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class UserService {

    private final List<User> userList = new ArrayList<>();
    private final AtomicLong nextId = new AtomicLong(4); 

    public UserService() {
        userList.add(new User(1L, "Juan", "Perez", "juan.perez@example.com"));
        userList.add(new User(2L, "Maria", "Gomez", "maria.gomez@example.com"));
        userList.add(new User(3L, "Carlos", "Lopez", "carlos.lopez@example.com"));
    }

    public UserResponseDTO createUser(User newUser) {
        newUser.setId(nextId.getAndIncrement());
        userList.add(newUser);

        String nombreUpper = newUser.getNombre() != null ? newUser.getNombre().toUpperCase() : "";
        String apellidoUpper = newUser.getApellido() != null ? newUser.getApellido().toUpperCase() : "";

        return new UserResponseDTO(nombreUpper, apellidoUpper);
    }

    public List<User> getAllUsers() {
        return new ArrayList<>(userList); 
    }

    public Optional<User> getUserById(Long id) {
        return userList.stream()
                       .filter(user -> user.getId().equals(id))
                       .findFirst();
    }
}