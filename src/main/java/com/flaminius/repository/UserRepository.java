package com.flaminius.repository;

import com.flaminius.domain.User;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserRepository {
    // In-memory storage for demonstration
    private static final Map<Integer, User> users = new HashMap<>();

    public User fetchById(int id){
        return users.get(id);
    }

    public List<User> fetchAll(){
        return users.values().stream().toList();
    }

    public void save(User user){
        // Ignoring collisions for now
        users.put(user.getId(), user);
    }

}
