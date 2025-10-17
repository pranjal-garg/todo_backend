package com.example.todo.service;

import com.example.todo.models.DashboardModel;
import com.example.todo.models.TaskModel;
import com.example.todo.models.UserModel;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DashboardService {
    private final UserService userService;
    private final TaskService taskService;

    DashboardService(UserService userService, TaskService taskService) {
        this.userService = userService;
        this.taskService = taskService;
    }

    public DashboardModel getData(String uid) {
        UserModel user = userService.getUserById(uid);
        List<TaskModel> tasks = taskService.getAllTasks(uid);
        return new DashboardModel(user, tasks);
    }
}
