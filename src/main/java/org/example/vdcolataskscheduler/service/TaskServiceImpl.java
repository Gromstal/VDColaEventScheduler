package org.example.vdcolataskscheduler.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.example.vdcolataskscheduler.dto.TaskDto;
import org.example.vdcolataskscheduler.dto.UserDto;
import org.example.vdcolataskscheduler.mapper.TaskMapper;
import org.example.vdcolataskscheduler.mapper.UserMapper;
import org.example.vdcolataskscheduler.repository.TaskRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.CacheManager;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class TaskServiceImpl implements TaskService {

    private final TaskRepository taskRepository;
    private final CacheManager cacheManager;
    private final TaskMapper taskMapper;
    private final UserMapper userMapper;
    private final UserService userService;
    private static final Logger logger = LoggerFactory.getLogger(TaskServiceImpl.class);


    public void addTask(TaskDto task) {
        taskRepository.save(taskMapper.toEntity(task, userMapper.toEntity(getUser())));
        cacheManager.getCache("tasksCache").evict("allTasks");
        logger.info("Task added successfully");
    }

    @Transactional
    public void updateTask(TaskDto task) {
        taskRepository.updateAllById(
                task.getId(),
                task.getDescription(),
                task.getCategory(),
                task.getDate());
        cacheManager.getCache("tasksCache").evict("allTasks");
        logger.info("Task " + task.getId() + " updated");
    }

    public TaskDto findTaskById(Long id) {
        return getTasks().stream()
                .filter(task -> task.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    public List<TaskDto> findAllTasks() {
        return getTasks();
    }

    private List<TaskDto> getTasks() {
        List<TaskDto> cachedTasks = getCachedTasks();
        return Objects.requireNonNullElseGet(cachedTasks, this::fetchAndCacheTasks);
    }

    private List<TaskDto> fetchAndCacheTasks() {
        List<TaskDto> resultList = taskMapper.toDtoList(taskRepository.findAllByUser_Id(getUser().getId()));
        resultList.sort(Comparator.comparing(TaskDto::getCreatedAt));
        cacheManager.getCache("tasksCache").put("allTasks", resultList);
        logger.info("Finding all tasks for {}", getUser().getLogin());

        return resultList;
    }

    private List<TaskDto> getCachedTasks() {
        var cache = cacheManager.getCache("tasksCache");
        if (cache != null) {
            var wrapper = cache.get("allTasks");
            if (wrapper != null) {
                return (List<TaskDto>) wrapper.get();
            }
        }
        return null;
    }

    private UserDto getUser() {
        return userService.getCurrentUser();
    }
}