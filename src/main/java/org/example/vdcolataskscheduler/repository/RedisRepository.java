package org.example.vdcolataskscheduler.repository;

import lombok.RequiredArgsConstructor;
import org.example.vdcolataskscheduler.dto.TaskDto;
import org.example.vdcolataskscheduler.service.UserService;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import java.time.Duration;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class RedisRepository {

    private final RedisTemplate<String, Object> redisTemplate;
    private final UserService userService;


    public void save(List<TaskDto> resultList){
        redisTemplate.opsForValue().set(getKey(), resultList, Duration.ofMinutes(10));
    }

    public void delete(){
        redisTemplate.delete(getKey());
    }

    public String getKey() {
        return "tasks:" + userService.getCurrentUser().getId();
    }

    public List<TaskDto> getTasks() {
        return (List<TaskDto>) redisTemplate.opsForValue().get(getKey());
    }
}
