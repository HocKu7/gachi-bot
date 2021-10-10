package com.github.telegramgachibot.telegram.command;

import java.util.List;

import com.github.telegramgachibot.entity.BotAnswerEntity;
import com.github.telegramgachibot.service.api.BotAnswerService;

import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.Chat;
import org.telegram.telegrambots.meta.api.objects.User;
import org.telegram.telegrambots.meta.bots.AbsSender;

@Component
public class AllGachiBotCommand extends AbstractBotCommand {

    private static final String COMMAND_IDENTIFIER = "all";

    private static final String DESCRIPTION = "Получить все гачи";

    private final BotAnswerService botAnswerService;

    public AllGachiBotCommand(BotAnswerService botAnswerService) {
        super(COMMAND_IDENTIFIER, DESCRIPTION);
        this.botAnswerService = botAnswerService;
    }

    @Override
    protected void handleCommand(AbsSender absSender, User user, Chat chat, String[] strings) throws Exception {

        List<BotAnswerEntity> all = botAnswerService.findAll();

        for (BotAnswerEntity botAnswerEntity : all) {
            sendAudio(botAnswerEntity, chat.getId(), absSender);
        }
    }
}
