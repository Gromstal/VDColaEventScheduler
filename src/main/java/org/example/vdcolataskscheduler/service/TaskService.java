package org.example.vdcolataskscheduler.service;

import org.example.vdcolataskscheduler.dto.TaskDto;
import org.example.vdcolataskscheduler.dto.UserDto;
import org.example.vdcolataskscheduler.entity.Task;

import java.util.List;

public interface TaskService {
    void addTask(TaskDto task);
    List<TaskDto> findAllTasks();
    void updateTask(TaskDto task);
    TaskDto findTaskById(Long id);
}
