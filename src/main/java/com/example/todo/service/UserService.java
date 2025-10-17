package com.example.todo.service;

import com.example.todo.models.UserModel;
import com.example.todo.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserModel addUser(UserModel user) {
        return userRepository.addUser(user);
    }
}
