package com.flaminius.service;

import com.flaminius.domain.User;
import com.flaminius.repository.UserRepository;

import java.util.List;

public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository myRepository) {
        this.userRepository = myRepository;
    }

    public List<User> getUsers() {
        return userRepository.fetchAll();
    }
    public User getUserById(int id) {
        return userRepository.fetchById(id);
    }

    public User createUser(User user) {
        return userRepository.save(user);
    }
}
