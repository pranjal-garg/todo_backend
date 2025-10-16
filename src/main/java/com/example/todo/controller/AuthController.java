package com.example.todo.controller;

import com.example.todo.models.ResponseModel;
import com.example.todo.models.UserModel;
import com.example.todo.service.AuthService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class AuthController {
    private final AuthService authService;
    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/register")
    public ResponseEntity<ResponseModel> register(@RequestHeader("uid") String uid, @RequestBody UserModel user) {
        user.setUid(uid);
        return authService.register(user);
    }
}
