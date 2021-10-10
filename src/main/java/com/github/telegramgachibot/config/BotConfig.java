package com.github.telegramgachibot.config;

import java.util.List;

import com.github.telegramgachibot.telegram.command.AbstractBotCommand;
import com.github.telegramgachibot.telegram.GachiBot;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

@Configuration
@EnableScheduling
public class BotConfig {

    @Bean
    public GachiBot telegramBot(List<AbstractBotCommand> abstractBotCommandList) {

        GachiBot gachiBot = new GachiBot();
        for (AbstractBotCommand botCommand : abstractBotCommandList) {

            gachiBot.register(botCommand);
        }
        return gachiBot;
    }

    @Bean
    public TelegramBotsApi telegramBotsApi(GachiBot bot) throws TelegramApiException {
        TelegramBotsApi telegramBotsApi = new TelegramBotsApi(DefaultBotSession.class);
        telegramBotsApi.registerBot(bot);
        return telegramBotsApi;
    }
}
