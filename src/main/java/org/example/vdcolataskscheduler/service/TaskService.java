package org.example.vdcolataskscheduler.service;

import org.example.vdcolataskscheduler.dto.TaskDto;
import org.example.vdcolataskscheduler.dto.UserDto;
import org.example.vdcolataskscheduler.entity.Task;

import java.util.List;

public interface TaskService {
    void addTask(UserDto user,TaskDto task);
    List<Task> findAllTasks(UserDto user);
    void updateTask(TaskDto task);
    TaskDto findTaskById(Long id);
}
