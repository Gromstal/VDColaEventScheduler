package org.example.vdcolataskscheduler.scheduler;

import lombok.RequiredArgsConstructor;
import org.example.vdcolataskscheduler.service.NotificationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

@Component
@RequiredArgsConstructor
public class TaskScheduleRunner {

    private final NotificationService notificationService;
    private static final Logger log = LoggerFactory.getLogger(TaskScheduleRunner.class);

    @Scheduled(cron = "0 0 5 * * ?")
    public void sendNotify() {
        try {
            notificationService.sendNotify();
        } catch (TelegramApiException e) {
            log.error("Ошибка при отправке уведомления через Telegram", e);
        } catch (Exception e) {
            log.error("Неизвестная ошибка при выполнении планировщика", e);
        }
    }
}
