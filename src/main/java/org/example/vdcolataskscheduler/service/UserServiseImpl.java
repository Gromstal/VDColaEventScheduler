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
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiseImpl implements UserServise {

    private final UserMapperMyImpl userMapperMy;

    public UserDto getCurrentUser() {
        User user =(User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        UserDto userDto = userMapperMy.toDto(user);
        return userDto;
    }
}