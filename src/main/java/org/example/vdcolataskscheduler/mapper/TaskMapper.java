package org.example.vdcolataskscheduler.mapper;

import org.example.vdcolataskscheduler.dto.TaskDto;
import org.example.vdcolataskscheduler.entity.Task;
import org.mapstruct.Mapper;

import java.util.List;


@Mapper(componentModel = "spring")
public interface TaskMapper {

    TaskDto toDto(Task task);
    Task toEntity(TaskDto taskDto);
    List<Task> toEntityList(List<TaskDto> taskDtoList);
    List<TaskDto> toDtoList (List<Task> taskList);
}