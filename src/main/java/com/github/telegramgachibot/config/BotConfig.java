package com.github.telegramgachibot.config;

import com.pengrad.telegrambot.TelegramBot;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;

@Configuration
@EnableScheduling
public class BotConfig {

    @Value("${application.telegram.api-key}")
    private String token;

    @Bean
    public TelegramBot telegramBot() {
        return new TelegramBot(token);
    }
}
