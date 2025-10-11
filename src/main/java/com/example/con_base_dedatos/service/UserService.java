package com.example.con_base_dedatos.service;



import com.example.con_base_dedatos.dto.UserDTO;
import com.example.con_base_dedatos.model.User;
import java.util.List;
public interface UserService {

    UserDTO createUser(User user);

    List<User> getAllUsers();

    User getUserById(Long id);

    void deleteUser(Long id);

    User updateUser(Long id, User user);
}

