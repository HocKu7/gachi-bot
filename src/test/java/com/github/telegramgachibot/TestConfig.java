package com.github.telegramgachibot;

import com.github.telegramgachibot.repo.BotAnswerRepository;
import com.github.telegramgachibot.service.BotAnswerService;
import com.github.telegramgachibot.service.BotAnswerServiceImpl;

import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;

@TestConfiguration
public class TestConfig {

    @Bean
    public BotAnswerService botAnswerService(BotAnswerRepository botAnswerRepository) {
        return new BotAnswerServiceImpl(botAnswerRepository);
    }
}
