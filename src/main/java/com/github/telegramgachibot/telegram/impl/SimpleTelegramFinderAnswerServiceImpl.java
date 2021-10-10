package com.github.telegramgachibot.telegram.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.Optional;

import com.github.telegramgachibot.entity.BotAnswerEntity;
import com.github.telegramgachibot.service.api.BotAnswerService;
import com.github.telegramgachibot.telegram.api.TelegramFinderAnswerService;

//@Service
@RequiredArgsConstructor
@Slf4j
public class SimpleTelegramFinderAnswerServiceImpl implements TelegramFinderAnswerService {

    private final BotAnswerService botAnswerService;

    @Override
    public Optional<BotAnswerEntity> findAnswer(List<String> args) {

        String arg = "Boss in this gym.mp3";
        if (!args.isEmpty()) {
            arg = String.join(" ", args);
        }
        List<BotAnswerEntity> botAnswerEntities = botAnswerService.findByFileName(arg);
        if (botAnswerEntities.isEmpty()) {
            return Optional.empty();
        }
        return Optional.ofNullable(botAnswerEntities.get(0));
    }
}
