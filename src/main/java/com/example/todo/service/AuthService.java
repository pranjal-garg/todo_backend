package com.example.todo.service;

import com.example.todo.models.ResponseModel;
import com.example.todo.models.UserModel;
import com.example.todo.repository.AuthRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class AuthService {
    final AuthRepository authRepository;

    public AuthService(AuthRepository authRepository) {
        this.authRepository = authRepository;
    }

    public ResponseEntity<ResponseModel> register(UserModel user) {
        ResponseModel responseModel = new ResponseModel();
        Map<String, Object> errors = new HashMap<>();
        if (user == null) {
            responseModel.setMessage("User cannot be empty");
            responseModel.setStatus(false);
            responseModel.setData(null);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseModel);
        }
        if (user.getUid().trim().isEmpty()) {
            errors.put("uid", "uid is needed");
        }
        if (user.getEmail().trim().isEmpty()) {
            errors.put("email", "email is needed");
        }
        if (user.getName().trim().isEmpty()) {
            errors.put("name", "name is needed");
        }
        if (!errors.isEmpty()) {
            responseModel.setStatus(false);
            responseModel.setMessage("User is not registered");
            responseModel.setData(errors);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseModel);
        }
        UserModel userModel = authRepository.addUser(user);
        responseModel.setStatus(true);
        responseModel.setMessage("User registered successfully");
        responseModel.setData(Map.of("user", userModel));
        return new ResponseEntity<>(responseModel, HttpStatus.OK);
    }
}
