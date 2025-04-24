package org.example.vdcolataskscheduler.telegrambot;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

@Component
public class TelegramBot extends TelegramLongPollingBot {

    private final String botName;
    private final String chatId;

    public TelegramBot(@Value("${telegrambots.botToken}") String botToken,
                       @Value("${telegrambots.botName}") String botName,
                       @Value("${telegrambots.chatId}") String chatId) {
        super(botToken);
        this.botName = botName;
        this.chatId = chatId;
    }

    public void sendMessage(String text) throws TelegramApiException {
        SendMessage message = new SendMessage();
        message.setChatId(chatId);
        message.setText(text);
        execute(message);
    }

    @Override
    public void onUpdateReceived(Update update) {

    }

    @Override
    public String getBotUsername() {
        return botName;
    }
}