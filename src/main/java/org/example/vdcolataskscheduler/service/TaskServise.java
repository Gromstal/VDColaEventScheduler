package org.example.vdcolataskscheduler.service;

import org.example.vdcolataskscheduler.dto.TaskDto;
import org.example.vdcolataskscheduler.dto.UserDto;
import org.example.vdcolataskscheduler.entity.Task;
import org.example.vdcolataskscheduler.entity.User;

import java.util.List;

public interface TaskServise {
    void addTask(UserDto user,TaskDto task);
    List<Task>  findAll(UserDto user);
}
