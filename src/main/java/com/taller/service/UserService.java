package com.taller.service;



import java.util.List;
import java.util.Optional;
import com.taller.DTO.UserFullDTO;
import com.taller.DTO.UserRequestDTO;
import com.taller.DTO.UserResponseDTO;

public interface UserService {
    UserResponseDTO createUser(UserRequestDTO requestDTO);
    List<UserFullDTO> getAllUsers();
    Optional<UserFullDTO> getUserById(Long id);
}