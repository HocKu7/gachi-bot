package com.github.telegramgachibot.telegram.command;

import java.util.Arrays;
import java.util.Optional;

import com.github.telegramgachibot.entity.BotAnswerEntity;
import com.github.telegramgachibot.telegram.api.TelegramFinderAnswerService;

import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.Chat;
import org.telegram.telegrambots.meta.api.objects.User;
import org.telegram.telegrambots.meta.bots.AbsSender;

@Component
public class AudioBotCommand extends AbstractBotCommand {

    private static final String COMMAND_IDENTIFIER = "gachi";

    private static final String DESCRIPTION = "Получить гачи";

    private final TelegramFinderAnswerService answerService;

    public AudioBotCommand(TelegramFinderAnswerService answerService) {
        super(COMMAND_IDENTIFIER, DESCRIPTION);
        this.answerService = answerService;
    }

    @Override
    protected void handleCommand(AbsSender absSender, User user, Chat chat, String[] strings) throws Exception {

        Optional<BotAnswerEntity> answerBox = answerService.findAnswer(Arrays.asList(strings));
        if (answerBox.isPresent()) {
            sendAudio(answerBox.get(), chat.getId(), absSender);
            return;
        }

        sendDefaultAnswer(chat.getId(), absSender);
    }
}
