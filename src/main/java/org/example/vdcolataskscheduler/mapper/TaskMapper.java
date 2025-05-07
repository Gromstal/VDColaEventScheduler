package org.example.vdcolataskscheduler.mapper;

import org.example.vdcolataskscheduler.dto.TaskDto;
import org.example.vdcolataskscheduler.entity.Task;
import org.example.vdcolataskscheduler.entity.User;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class TaskMapper {

    public TaskDto toDto(Task task) {
        if (task == null) {
            return null;
        }

        TaskDto taskDto = new TaskDto();

        taskDto.setId(task.getId());
        taskDto.setCategory(task.getCategory());
        taskDto.setDescription( task.getDescription());
        taskDto.setDate(task.getDate());
        taskDto.setNotified(task.isNotified());
        taskDto.setCreatedAt(task.getCreatedAt());

        return taskDto;
    }

    public Task toEntity(TaskDto taskDto, User user) {
        if (taskDto == null) {
            return null;
        }

        Task task = new Task();
        task.setDescription(taskDto.getDescription());
        task.setCategory(taskDto.getCategory());
        task.setNotified(taskDto.isNotified());
        task.setDate(taskDto.getDate());
        task.setUser(user);

        return task;
    }

    public List<TaskDto> toDtoList(List<Task> taskList) {
        if (taskList == null) {
            return null;
        }

        List<TaskDto> list = new ArrayList<TaskDto>(taskList.size());
        for (Task task : taskList) {
            list.add(toDto(task));
        }

        return list;
    }
}