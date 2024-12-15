package com.flaminius;

import static spark.Spark.*;
import com.flaminius.domain.User;
import com.google.gson.Gson;

import java.util.HashMap;
import java.util.Map;

public class Main {

    // In-memory storage for demonstration
    private static Map<Integer, User> users = new HashMap<>();
    private static Gson gson = new Gson();

    public static void main(String[] args) {
        // Start Spark on a specific port
        port(8080);

        // Create a new user (POST /users)
        post("/users", (req, res) -> {
            User user = gson.fromJson(req.body(), User.class);
            users.put(user.getId(), user);
            res.status(201); // Created
            return gson.toJson(user);
        });

        // Retrieve a user by ID (GET /users/:id)
        get("/users/:id", (req, res) -> {
            int userId = Integer.parseInt(req.params("id"));
            User user = users.get(userId);

            if (user == null) {
                res.status(404); // Not Found
                return gson.toJson(Map.of("message", "User not found"));
            }
            res.status(200); // OK
            return gson.toJson(user);
        });

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
        });

        // List all users (GET /users)
        get("/users", (req, res) -> gson.toJson(users.values()));
    }
}