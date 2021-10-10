package com.github.telegramgachibot.telegram.command;

import lombok.extern.slf4j.Slf4j;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import com.github.telegramgachibot.entity.BotAnswerEntity;

import org.telegram.telegrambots.extensions.bots.commandbot.commands.BotCommand;
import org.telegram.telegrambots.meta.api.methods.send.SendAudio;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Chat;
import org.telegram.telegrambots.meta.api.objects.InputFile;
import org.telegram.telegrambots.meta.api.objects.User;
import org.telegram.telegrambots.meta.bots.AbsSender;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

@Slf4j
public abstract class AbstractBotCommand extends BotCommand {
    public AbstractBotCommand(String commandIdentifier, String description) {
        super(commandIdentifier, description);
    }

    @Override
    public void execute(AbsSender absSender, User user, Chat chat, String[] strings) {
        try {

            handleCommand(absSender, user, chat, strings);
        } catch (Exception e) {
            new SendMessage(chat.getId().toString(), "шото поломалось");
            log.error(String.format("Ошибка %s. Команда %s, Пользователь: %s", e.getMessage(),
                    getCommandIdentifier(), user.getUserName()));
            e.printStackTrace();
        }
    }

    protected void sendAudio(BotAnswerEntity botAnswerEntity, Long chatId, AbsSender absSender) throws TelegramApiException {
        InputFile inputFile = new InputFile();
        InputStream inputStream = new ByteArrayInputStream(botAnswerEntity.getContent());
        String fileName = botAnswerEntity.getFileName();
        inputFile.setMedia(inputStream, fileName);
        SendAudio sendAudio = SendAudio.builder()
                .audio(inputFile)
                .chatId(String.valueOf(chatId))
                .duration(3)
                .build();
        absSender.execute(sendAudio);
        log.debug("Файл fileName={} отправлен в чат chatId={}", fileName, chatId);
    }

    protected void sendDefaultAnswer(Long chatId, AbsSender absSender) throws TelegramApiException {

        sendTextMessage("Спроси шо попроще...", chatId, absSender);
    }

    protected void sendTextMessage(String message, Long chatId, AbsSender absSender) throws TelegramApiException {

        SendMessage sendMessage = SendMessage.builder()
                .chatId(String.valueOf(chatId))
                .text(message)
                .build();
        absSender.execute(sendMessage);
        log.debug("Сообщение:\"{}\" отправлено в чат chatId={}", message, chatId);
    }

    protected abstract void handleCommand(AbsSender absSender, User user, Chat chat, String[] strings) throws Exception;
}
