package com.flaminius.controller;

import com.flaminius.domain.User;
import com.flaminius.service.UserService;
import com.google.gson.Gson;

import java.util.Map;

import static spark.Spark.*;

public class UserController {

    private static Gson gson = new Gson();

    private final UserService userService;

    public UserController(UserService userService){
        this.userService = userService;

        // Create a new user (POST /users)
        post("/users", (req, res) -> {
            User user = gson.fromJson(req.body(), User.class);
            userService.createUser(user);
            res.status(201); // Created
            return gson.toJson(user);
        });

        // Retrieve a user by ID (GET /users/:id)
        get("/users/:id", (req, res) -> {
            int userId = Integer.parseInt(req.params("id"));
            User user = userService.getUserById(userId);

            if (user == null) {
                res.status(404); // Not Found
                return gson.toJson(Map.of("message", "User not found"));
            }
            res.status(200); // OK
            return gson.toJson(user);
        });

        // List all users (GET /users)
        get("/users", (req, res) ->
                gson.toJson(userService.getUsers()));

    }
}

/*
// Update a user by ID (PUT /users/:id)
put("/users/:id", (req, res) -> {
    int userId = Integer.parseInt(req.params("id"));
    User user = gson.fromJson(req.body(), User.class);

    if (!users.containsKey(userId)) {
        res.status(404); // Not Found
        return gson.toJson(Map.of("message", "User not found"));
    }

    users.put(userId, user);
    res.status(200); // OK
    return gson.toJson(user);
});

// Delete a user by ID (DELETE /users/:id)
delete("/users/:id", (req, res) -> {
    int userId = Integer.parseInt(req.params("id"));

    if (users.remove(userId) == null) {
        res.status(404); // Not Found
        return gson.toJson(Map.of("message", "User not found"));
    }
    res.status(204); // No Content
    return "";
});*/