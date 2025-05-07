package org.example.vdcolataskscheduler.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.example.vdcolataskscheduler.dto.TaskDto;
import org.example.vdcolataskscheduler.dto.UserDto;
import org.example.vdcolataskscheduler.mapper.TaskMapper;
import org.example.vdcolataskscheduler.mapper.UserMapper;
import org.example.vdcolataskscheduler.repository.RedisRepository;
import org.example.vdcolataskscheduler.repository.TaskRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TaskServiceImpl implements TaskService {

    private final TaskRepository taskRepository;
    private final RedisRepository redisRepository;
    private final TaskMapper taskMapper;
    private final UserMapper userMapper;
    private final UserService userService;
    private static final Logger logger = LoggerFactory.getLogger(TaskServiceImpl.class);

    public void addTask(TaskDto task) {
        taskRepository.save(taskMapper.toEntity(task, userMapper.toEntity(getUser())));
        redisRepository.delete();
        logger.info("Task added successfully");
    }

    @Transactional
    public void updateTask(TaskDto task) {
        taskRepository.updateAllById(
                task.getId(),
                task.getDescription(),
                task.getCategory(),
                task.getDate());
        redisRepository.delete();
        logger.info("Task " + task.getId() + " updated");
    }

    public TaskDto findTaskById(Long id) {
        List<TaskDto> resultList = redisRepository.getTasks();
        if (resultList != null) {
            return resultList
                    .stream()
                    .filter(task -> task.getId().equals(id))
                    .findFirst().orElse(null);
        }

        return taskMapper.toDto(taskRepository.findById(id).orElse(null));
    }

    public List<TaskDto> findAllTasks() {
        List<TaskDto> resultList = redisRepository.getTasks();
        if (resultList == null) {
            resultList = taskMapper.toDtoList(taskRepository.findAllByUser_Id(getUser().getId()));
            redisRepository.save(resultList);
            resultList.sort(Comparator.comparing(TaskDto::getCreatedAt));
        }

        logger.info("Finding all tasks for {}", getUser().getLogin());

        return resultList;
    }

    private UserDto getUser() {
        return userService.getCurrentUser();
    }
}