package com.CustomerRelationshipManagement.controller;

import com.CustomerRelationshipManagement.entity.User;
import com.CustomerRelationshipManagement.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody Map<String, String> request) {
        try {
            String fullName = request.get("fullName");
            String email = request.get("email");
            String password = request.get("password");

            if (fullName == null || fullName.isBlank()
                    || email == null || email.isBlank()
                    || password == null || password.length() < 8) {

                return ResponseEntity.badRequest()
                        .body(Map.of(
                                "message",
                                "Name and email are required. Password must contain at least 8 characters."
                        ));
            }

            User user = userService.registerUser(
                    fullName.trim(),
                    email.trim().toLowerCase(),
                    password
            );

            return ResponseEntity.ok(
                    Map.of(
                            "message", "Registration successful!",
                            "userId", user.getId()
                    )
            );

        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest()
                    .body(Map.of("message", e.getMessage()));
        }
    }
}