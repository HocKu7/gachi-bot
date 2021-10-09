package com.github.telegramgachibot.service.api;

import java.util.Optional;

import com.github.telegramgachibot.entity.BotAnswerEntity;

public interface TelegramFinderAnswerService {

    Optional<BotAnswerEntity> findAnswer(String inputMessage);
}
