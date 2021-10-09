package com.github.telegramgachibot;

import com.github.telegramgachibot.repo.BotAnswerDao;
import com.github.telegramgachibot.service.api.BotAnswerService;
import com.github.telegramgachibot.service.impl.BotAnswerServiceImpl;

import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;

@TestConfiguration
public class TestConfig {

    @Bean
    public BotAnswerService botAnswerService(BotAnswerDao botAnswerDao) {
        return new BotAnswerServiceImpl(botAnswerDao);
    }
}
