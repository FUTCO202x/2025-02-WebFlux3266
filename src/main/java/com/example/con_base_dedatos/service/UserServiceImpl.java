package com.example.con_base_dedatos.service;



import com.example.con_base_dedatos.dto.UserDTO;
import com.example.con_base_dedatos.model.User;
import com.example.con_base_dedatos.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDTO createUser(User user) {
        User savedUser = userRepository.save(user);
        return new UserDTO(savedUser.getNombre(), savedUser.getApellido());
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public User getUserById(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    @Override
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public User updateUser(Long id, User user) {
        Optional<User> existingUser = userRepository.findById(id);
        if (existingUser.isPresent()) {
            User u = existingUser.get();
            u.setNombre(user.getNombre());
            u.setApellido(user.getApellido());
            u.setEmail(user.getEmail());
            return userRepository.save(u);
        }
        return null;
    }
}
