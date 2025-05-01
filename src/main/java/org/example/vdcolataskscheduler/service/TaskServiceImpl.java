package org.example.vdcolataskscheduler.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.example.vdcolataskscheduler.dto.TaskDto;
import org.example.vdcolataskscheduler.dto.UserDto;
import org.example.vdcolataskscheduler.entity.Task;
import org.example.vdcolataskscheduler.mapper.TaskMapper;
import org.example.vdcolataskscheduler.mapper.UserMapper;
import org.example.vdcolataskscheduler.repository.TaskRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import java.time.Duration;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TaskServiceImpl implements TaskService {

    private static final Logger logger = LoggerFactory.getLogger(TaskServiceImpl.class);
    private final TaskRepository taskRepository;
    private final TaskMapper taskMapper;
    private final UserMapper userMapper;
    private final RedisTemplate<String, Object> redisTemplate;
    private final UserService userService;

    public void addTask(TaskDto task) {
        task.setUser(userMapper.toEntity(getUser()));
        Task taskEntity = taskMapper.toEntity(task);
        taskRepository.save(taskEntity);
        redisTemplate.delete(getKey());
        logger.info("Task added successfully");
    }

    @Transactional
    public void updateTask(TaskDto task) {
        taskRepository.updateAllById(
                task.getId(),
                task.getDescription(),
                task.getCategory(),
                task.getDate());
        redisTemplate.delete(getKey());
        logger.info("Task " + task.getId() + " updated");
    }

    public TaskDto findTaskById(Long id) {

        List<TaskDto> resultList = getTasks();
        if (resultList != null) {
            return resultList
                    .stream()
                    .filter(task -> task.getId().equals(id))
                    .findFirst().orElse(null);
        }

        return taskMapper.toDto(taskRepository.findById(id).orElse(null));
    }

    public List<TaskDto> findAllTasks() {

        List<TaskDto> resultList = getTasks();
        if (resultList == null) {
            resultList = taskMapper.toDtoList(taskRepository.findAllByUser(userMapper.toEntity(getUser())));
            redisTemplate.opsForValue().set(getKey(), resultList, Duration.ofMinutes(10));
        }

        logger.info("Finding all tasks for {}", getUser().getLogin());

        return resultList;
    }


    private String getKey() {
        return "tasks:" + userService.getCurrentUser().getId();
    }

    private UserDto getUser(){
        return userService.getCurrentUser();
    }

    private List<TaskDto> getTasks(){
        return (List<TaskDto>) redisTemplate.opsForValue().get(getKey());
    }
}