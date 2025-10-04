package com.taller.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.taller.DTO.UserFullDTO;
import com.taller.DTO.UserRequestDTO;
import com.taller.DTO.UserResponseDTO;
import com.taller.model.User;
import com.taller.repository.UserRepository;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserResponseDTO createUser(UserRequestDTO requestDTO) {
        User user = new User();
        user.setNombre(requestDTO.getNombre());
        user.setApellido(requestDTO.getApellido());
        user.setEmail(requestDTO.getEmail());
        User savedUser = userRepository.save(user);
        return new UserResponseDTO(savedUser.getNombre(), savedUser.getApellido());
    }

    @Override
    public List<UserFullDTO> getAllUsers() {
        return userRepository.findAll().stream()
                .map(UserFullDTO::new)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<UserFullDTO> getUserById(Long id) {
        return userRepository.findById(id).map(UserFullDTO::new);
    }

	
}