package com.example.todo.controller;

import com.example.todo.models.TaskModel;
import com.example.todo.service.TaskService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class TaskController {
    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @PostMapping("/task")
    public TaskModel addTask(@RequestBody TaskModel taskModel, @RequestHeader("uid") String uid) {
        taskModel.setUid(uid);
        return taskService.addTask(taskModel);
    }
}

