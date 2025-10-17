package com.example.todo.service;

import com.example.todo.Exceptions.models.CustomException;
import com.example.todo.models.TaskModel;
import com.example.todo.models.UserModel;
import com.example.todo.repository.TaskRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskService {
    private final TaskRepository taskRepository;
    private final UserService userService;


    TaskService(TaskRepository taskRepository, UserService userService) {
        this.taskRepository = taskRepository;
        this.userService = userService;
    }

    public TaskModel addTask(TaskModel task) {
        UserModel userModel = userService.getUserById(task.getUid());
        if (userModel == null) {
            throw new CustomException(HttpStatus.NOT_FOUND, "User not found");
        }
        task.setTaskId("TASK#" + (userModel.getTasks() + 1));
        task.setCompleted(false);
        userModel.setTasks(userModel.getTasks() + 1);
        userService.updateUser(userModel);

        return taskRepository.addTask(task);
    }

    public List<TaskModel> getAllTasks(String uid) {
        List<TaskModel> tasks = taskRepository.getAllTasks(uid);
        return tasks;
    }
}
