package com.github.telegramgachibot.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

import javax.transaction.Transactional;

import com.github.telegramgachibot.entity.BotAnswerEntity;
import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.model.Message;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.GetUpdates;
import com.pengrad.telegrambot.request.SendAudio;
import com.pengrad.telegrambot.response.GetUpdatesResponse;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
//@ConditionalOnProperty(value = "${application.telegram.bot-enable}")
public class TelegramUpdateContextServiceImpl implements TelegramUpdateContextService {

    private final TelegramBot telegramBot;

    private final BotAnswerService botAnswerService;

    @Override
    @Scheduled(fixedDelay = 1000L)
    @Transactional
    public void updateContext() {

        GetUpdates getUpdates = new GetUpdates().limit(100).offset(0).timeout(0);
        GetUpdatesResponse updatesResponse = telegramBot.execute(getUpdates);
        List<Update> updates = updatesResponse.updates();

        for (Update update : updates) {

            Message message = update.message();
            Long chatId = message.chat().id();
            log.debug("Got message charId = {}, message {}", chatId, message.text());

            BotAnswerEntity botAnswerEntity = botAnswerService.getById(1L);
            SendAudio sendMessageRequest = new SendAudio(chatId, botAnswerEntity.getContent());

            telegramBot.execute(sendMessageRequest);
            log.debug("Send response chatId = {}", chatId);

            //fixme test
            break;
        }
    }
}
