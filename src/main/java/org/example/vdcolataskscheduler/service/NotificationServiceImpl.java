package org.example.vdcolataskscheduler.service;

import lombok.RequiredArgsConstructor;
import org.example.vdcolataskscheduler.dto.TaskDto;
import org.example.vdcolataskscheduler.mapper.TaskMapper;
import org.example.vdcolataskscheduler.repository.TaskRepository;
import org.example.vdcolataskscheduler.telegrambot.TelegramBot;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class NotificationServiceImpl implements NotificationService {

    private final TelegramBot telegramBot;
    private final TaskRepository taskRepository;
    private final TaskMapper taskMapper;
    private static final Logger logger = LoggerFactory.getLogger(NotificationServiceImpl.class);

    @Override
    @Transactional
    public void sendNotify() {

        for (TaskDto task : getActualTasks()) {
            try {
                telegramBot.sendMessage("Привет! Через 3 дня наступает запланированное событие - " + task.getDescription());
                logger.info("Уведомление успешно отправлено по событию id={}, description='{}'", task.getId(), task.getDescription());
                taskRepository.updateIsNotifiedById(task.getId());
                Thread.sleep(1000);
            } catch (TelegramApiException e) {
                logger.warn("Ошибка при отправке уведомления по событию id={}", task.getId(), e);
            } catch (Exception e) {
                logger.error("Неизвестная ошибка при отправке уведомления по событию id={}", task.getId(), e);
            }
        }
    }

    private List<TaskDto> getActualTasks() {
        List<TaskDto> tasks = taskMapper.toDtoList(taskRepository.findAll());
        return tasks.stream()
                .filter(task -> !task.isNotified() && !task.getDate().isAfter(LocalDate.now().plusDays(3)))
                .toList();
    }
}