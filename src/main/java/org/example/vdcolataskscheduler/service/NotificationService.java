package org.example.vdcolataskscheduler.service;

import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

public interface NotificationService {
    void sendNotify() throws TelegramApiException;
}
