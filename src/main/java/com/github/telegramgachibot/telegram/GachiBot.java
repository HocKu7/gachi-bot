package com.github.telegramgachibot.telegram;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Value;
import org.telegram.telegrambots.extensions.bots.commandbot.TelegramLongPollingCommandBot;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;

@Slf4j
public class GachiBot extends TelegramLongPollingCommandBot {

    @Value("${application.telegram.bot-username}")
    private String botUserName;

    @Value("${application.telegram.api-key}")
    private String token;

    @Override
    public void processNonCommandUpdate(Update update) {

        if (update == null) {
            return;
        }
        Message message = update.getMessage();
        if (message != null) {
            log.debug("Получил сообщение: " + message.getText());
        }
    }

    @Override
    public String getBotToken() {
        return token;
    }

    @Override
    public String getBotUsername() {
        return botUserName;
    }

}
