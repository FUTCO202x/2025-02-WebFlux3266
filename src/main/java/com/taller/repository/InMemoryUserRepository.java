package com.taller.repository;


import org.springframework.stereotype.Repository;

import com.taller.model.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

@Repository
public class InMemoryUserRepository implements UserRepository {
    private final List<User> users = new ArrayList<>();
    private final AtomicLong idGenerator = new AtomicLong(4); // Start from 4 after defaults

    public InMemoryUserRepository() {
        // Initialize with 3 default users
        users.add(new User(1L, "Juan", "Perez", "juan@example.com"));
        users.add(new User(2L, "Maria", "Lopez", "maria@example.com"));
        users.add(new User(3L, "Carlos", "Gomez", "carlos@example.com"));
    }

    @Override
    public User save(User user) {
        if (user.getId() == null) {
            user.setId(idGenerator.getAndIncrement());
        }
        users.add(user);
        return user;
    }

    @Override
    public List<User> findAll() {
        return new ArrayList<>(users);
    }

    @Override
    public Optional<User> findById(Long id) {
        return users.stream().filter(u -> u.getId().equals(id)).findFirst();
    }


}