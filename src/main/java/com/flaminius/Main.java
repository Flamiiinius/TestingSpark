package com.flaminius;

import static spark.Spark.*;

import com.flaminius.controller.UserController;
import com.flaminius.repository.UserRepository;
import com.flaminius.service.UserService;

public class Main {

    public static void main(String[] args) {
        // Instantiate the repository, service and controller layers
        UserRepository userRepository = new UserRepository();
        UserService userService = new UserService(userRepository);
        new UserController(userService);

        // Start Spark on a specific port
        port(8080);

        // Testing it's responding
        get("/", (req, res) -> {

            res.status(200); // OK
            return "Server is responding!";
        });


    }
}