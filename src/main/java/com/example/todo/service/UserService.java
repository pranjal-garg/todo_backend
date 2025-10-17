package com.example.todo.service;

import com.example.todo.Exceptions.models.CustomException;
import com.example.todo.models.UserModel;
import com.example.todo.repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserModel addUser(UserModel user) {
        user.setTasks(0);
        return userRepository.addUser(user);
    }

    public UserModel getUserById(String uid) {

        UserModel userModel = userRepository.getUserById(uid);
        System.out.println("UserService: "+(userModel==null));
        if (userModel == null) {
            System.out.println("UserModel is null");
            throw new CustomException(HttpStatus.NOT_FOUND, "User not found");
        }
        return userModel;
    }

    public UserModel updateUser(UserModel user) {
        userRepository.updateUser(user);
        return user;
    }
}
