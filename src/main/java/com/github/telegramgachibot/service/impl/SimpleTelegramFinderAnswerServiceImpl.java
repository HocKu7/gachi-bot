package com.github.telegramgachibot.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.Optional;

import com.github.telegramgachibot.entity.BotAnswerEntity;
import com.github.telegramgachibot.service.api.BotAnswerService;
import com.github.telegramgachibot.service.api.TelegramFinderAnswerService;

import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class SimpleTelegramFinderAnswerServiceImpl implements TelegramFinderAnswerService {

    private final BotAnswerService botAnswerService;

    @Override
    public Optional<BotAnswerEntity> findAnswer(String inputMessage) {

        List<BotAnswerEntity> botAnswerEntities = botAnswerService.findByFileName(inputMessage);
        if (botAnswerEntities.isEmpty()) {
            return Optional.empty();
        }
        return Optional.ofNullable(botAnswerEntities.get(0));
    }
}
