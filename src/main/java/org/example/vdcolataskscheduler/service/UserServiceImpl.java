package org.example.vdcolataskscheduler.service;

import lombok.RequiredArgsConstructor;
import org.example.vdcolataskscheduler.dto.UserDto;
import org.example.vdcolataskscheduler.entity.User;
import org.example.vdcolataskscheduler.mapper.UserMapper;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserMapper userMapper;

    public UserDto getCurrentUser() {
        User user =(User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return userMapper.toDto(user);
    }
}