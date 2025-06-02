package org.example.vdcolataskscheduler.service;

import org.example.vdcolataskscheduler.data.TestData;
import org.example.vdcolataskscheduler.dto.TaskDto;
import org.example.vdcolataskscheduler.mapper.TaskMapper;
import org.example.vdcolataskscheduler.repository.TaskRepository;
import org.example.vdcolataskscheduler.telegrambot.TelegramBot;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class NotificationServiceImplTest {

    TestData testData = new TestData();

    @Mock
    private TaskRepository taskRepository;

    @Mock
    private TelegramBot telegramBot;

    @Mock
    private TaskMapper taskMapper;

    @InjectMocks
    private  NotificationServiceImpl notificationService;

    @Test
    void sendNotify() throws Exception {

        List<TaskDto> allTasks = testData.getTaskList();
        when(taskRepository.findAll()).thenReturn(Collections.emptyList());
        when(taskMapper.toDtoList(anyList())).thenReturn(allTasks);
        notificationService.sendNotify();

        verify(telegramBot, times(3)).sendMessage(anyString());
        verify(taskRepository, times(1)).findAll();
    }

    @Test
    void sendNotifyFiveDays() throws Exception {

        LocalDate baseDate = LocalDate.now();
        TaskDto task = testData.getTask();

        for (int i = 0; i < 5; i++) {
            LocalDate simulatedDate = baseDate.plusDays(i);
            task.setDate(simulatedDate);

            when(taskRepository.findAll()).thenReturn(Collections.emptyList());
            when(taskMapper.toDtoList(anyList())).thenReturn(List.of(task));

            notificationService.sendNotify();
        }

        verify(taskRepository, times(5)).findAll();
        verify(telegramBot, times(3)).sendMessage(contains("Событие 1"));
    }
}