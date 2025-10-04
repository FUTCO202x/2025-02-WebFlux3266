package com.taller.repository;

import java.util.List;
import java.util.Optional;
import com.taller.model.User;

public interface UserRepository {
    User save(User user);
    List<User> findAll();
    Optional<User> findById(Long id);
}