package com.github.telegramgachibot.telegram.api;

import java.util.List;
import java.util.Optional;

import com.github.telegramgachibot.entity.BotAnswerEntity;

public interface TelegramFinderAnswerService {

    Optional<BotAnswerEntity> findAnswer(List<String> args);
}
