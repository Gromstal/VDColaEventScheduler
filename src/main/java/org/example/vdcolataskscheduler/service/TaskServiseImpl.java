package org.example.vdcolataskscheduler.service;

import lombok.RequiredArgsConstructor;
import org.example.vdcolataskscheduler.dto.TaskDto;
import org.example.vdcolataskscheduler.dto.UserDto;
import org.example.vdcolataskscheduler.entity.Task;
import org.example.vdcolataskscheduler.entity.User;
import org.example.vdcolataskscheduler.mapper.TaskMapper;
import org.example.vdcolataskscheduler.mapper.UserMapperMyImpl;
import org.example.vdcolataskscheduler.repository.TaskRepository;
import org.example.vdcolataskscheduler.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TaskServiseImpl implements TaskServise {

    private static final Logger logger = LoggerFactory.getLogger(TaskServiseImpl.class);
    private final TaskRepository taskRepository;
    private final TaskMapper taskMapper;
    private final UserRepository userRepository;
    private final UserMapperMyImpl userMapperMy;

    public void addTask(UserDto userDto,TaskDto taskDto) {
        taskDto.setUser(userMapperMy.toEntity(userDto));
        Task task = taskMapper.toEntity(taskDto);
        taskRepository.save(task);
        logger.info("Task added successfully");
    }

    public List<Task> findAll(UserDto userDto) {
        User user = userRepository.findByLogin(userDto.getLogin());
        logger.info("Finding all tasks for {}", user.getLogin());
        List<Task> tasks = user.getTaskList(); // не получаем LazyInitException из за того, что в рамках одного запроса достаем таски
        userDto.setTaskList(tasks);

        return userDto.getTaskList();
    }
}