package com.github.telegramgachibot.service.telegram;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import com.github.telegramgachibot.service.api.TelegramFinderAnswerService;
import com.github.telegramgachibot.service.api.TelegramUpdateHandler;
import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.model.Message;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.SendAudio;

import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class TelegramUpdateHandlerImpl implements TelegramUpdateHandler {

    private final TelegramBot telegramBot;

    private final TelegramFinderAnswerService telegramFinderAnswerService;

    @Override
    public void handleUpdate(Update update) {
        Message message = update.message();
        Long chatId = message.chat().id();
        String textMessage = message.text();
        log.debug("Got message charId = {}, message {}", chatId, textMessage);

        telegramFinderAnswerService.findAnswer(textMessage)
                .ifPresent(botAnswerEntity -> {
                    SendAudio sendMessageRequest = new SendAudio(chatId, botAnswerEntity.getContent());
                    telegramBot.execute(sendMessageRequest);
                    log.debug("Send response chatId = {}", chatId);
                });
    }
}
