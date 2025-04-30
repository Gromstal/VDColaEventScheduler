package org.example.vdcolataskscheduler.service;

import jakarta.transaction.Transactional;
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
public class TaskServiceImpl implements TaskService {

    private static final Logger logger = LoggerFactory.getLogger(TaskServiceImpl.class);
    private final TaskRepository taskRepository;
    private final TaskMapper taskMapper;
    private final UserRepository userRepository;
    private final UserMapperMyImpl userMapperMy;

    public void addTask(UserDto user,TaskDto task) {
        task.setUser(userMapperMy.toEntity(user));
        Task taskEntity = taskMapper.toEntity(task);
        taskRepository.save(taskEntity);
        logger.info("Task added successfully");
    }

    @Override
    public TaskDto findTaskById(Long id) {
        return taskMapper.toDto(taskRepository.findById(id).orElse(null));
    }

    public List<Task> findAllTasks(UserDto user) {
        User userEntity = userRepository.findByLogin(user.getLogin());
        logger.info("Finding all tasks for {}", userEntity.getLogin());
        List<Task> tasks = userEntity.getTaskList();
        user.setTaskList(tasks);

        return user.getTaskList();
    }

    @Transactional
    public void updateTask(TaskDto task) {
        taskRepository.updateAllById(
                task.getId(),
                task.getDescription(),
                task.getCategory(),
                task.getDate());
        logger.info("Task " + task.getId() + " updated");
    }
}