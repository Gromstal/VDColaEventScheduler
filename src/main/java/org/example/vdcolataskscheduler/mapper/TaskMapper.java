package org.example.vdcolataskscheduler.mapper;

import org.example.vdcolataskscheduler.dto.TaskDto;
import org.example.vdcolataskscheduler.entity.Task;
import org.mapstruct.Mapper;


@Mapper(componentModel = "spring")
public interface TaskMapper {

    TaskDto toDto(Task task);
    Task toEntity(TaskDto taskDto);

}