package com.example.todo.models;

import java.util.List;


public class DashboardModel {
    private final UserModel user;
    private final List<TaskModel> tasks;

    public DashboardModel(UserModel user, List<TaskModel> tasks) {
        this.user = user;
        this.tasks = tasks;
    }

    public UserModel getUser() {
        return user;
    }

    public List<TaskModel> getTasks() {
        return tasks;
    }
}
