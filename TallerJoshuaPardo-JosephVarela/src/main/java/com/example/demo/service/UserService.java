package com.example.demo.service;

import com.example.demo.model.User;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {
    private List<User> users = new ArrayList<>();

    public UserService() {
        users.add(new User(1, "Joshua", "Pardo", "joshuapardo@gmail.com"));
        users.add(new User(2, "Joseph", "Varela", "josephvarela@gmail.com"));
        users.add(new User(3, "Johanny", "Valencia", "johannysan@gmail.com"));
    }

    public List<User> getAllUsers() {
        return users;
    }

    public User getUserById(int id) {
        return users.stream().filter(u -> u.getId() == id).findFirst().orElse(null);
    }

    public User addUser(User user) {
        users.add(user);
        return user;
    }
}
