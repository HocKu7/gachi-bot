package com.github.telegramgachibot.service.telegram;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

import javax.transaction.Transactional;

import com.github.telegramgachibot.service.api.CommandSettingService;
import com.github.telegramgachibot.service.api.TelegramUpdateContextService;
import com.github.telegramgachibot.service.api.TelegramUpdateHandler;
import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.GetUpdates;
import com.pengrad.telegrambot.response.GetUpdatesResponse;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
@ConditionalOnProperty(value = "${application.telegram.bot-enable}", havingValue = "true")
public class TelegramUpdateContextServiceImpl implements TelegramUpdateContextService {

    private final TelegramBot telegramBot;

    private final TelegramUpdateHandler telegramUpdateHandler;

    private final CommandSettingService commandSettingService;

    @Value("${application.telegram.limit}")
    private Integer limitRequest;

    @Override
    @Scheduled(fixedDelay = 3000L)
    @Transactional
    public void updateContext() {

        Integer currentOffset = commandSettingService.getCurrentOffset();
        GetUpdates getUpdates = new GetUpdates()
                .limit(limitRequest)
                .offset(currentOffset)
                .timeout(0);
        GetUpdatesResponse updatesResponse = telegramBot.execute(getUpdates);
        List<Update> updates = updatesResponse.updates();

        //todo добавить ForkJoinPool
        for (Update update : updates) {

            telegramUpdateHandler.handleUpdate(update);
        }
    }
}
