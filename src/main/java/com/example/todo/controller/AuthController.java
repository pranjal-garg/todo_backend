package com.example.todo.controller;

import com.example.todo.models.UserModel;
import com.example.todo.service.UserService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class AuthController {
    private final UserService userService;

    public AuthController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public UserModel signup(@RequestHeader("uid") String uid, @RequestBody UserModel user) {
        user.setUid(uid);
        return userService.addUser(user);
    }
}
