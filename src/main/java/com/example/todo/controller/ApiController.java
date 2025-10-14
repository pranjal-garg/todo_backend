package com.example.todo.controller;

import com.example.todo.models.UserModel;
import com.example.todo.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class ApiController {
    private final UserService userService;

    public ApiController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/signup")
    public ResponseEntity<UserModel> signup(@RequestHeader("uid") String uid, @RequestBody UserModel user) {
        System.out.println("Recieved Signup Request");
        System.out.println("uid from authToken: " + uid);
        user.setUid(uid);
        try {
            UserModel createdUser = userService.addUser(user);
            System.out.println("User created successfully: " + uid);
            return ResponseEntity.status(HttpStatus.CREATED).body(createdUser);
        } catch (Exception e) {
            System.out.println("Error in signup: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);

        }
    }
}
