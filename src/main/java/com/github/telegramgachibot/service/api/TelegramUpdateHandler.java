package com.github.telegramgachibot.service.api;

import com.pengrad.telegrambot.model.Update;

public interface TelegramUpdateHandler {

    void handleUpdate(Update update);
}
