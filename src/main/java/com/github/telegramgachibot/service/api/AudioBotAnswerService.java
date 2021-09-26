package com.github.telegramgachibot.service.api;

import com.github.telegramgachibot.entity.AudioBotAnswerEntity;
import com.github.telegramgachibot.entity.BotAnswerEntity;

import org.springframework.web.multipart.MultipartFile;

public interface AudioBotAnswerService {

    BotAnswerEntity createByFile(MultipartFile multipartFile);

    AudioBotAnswerEntity getById(Long id);
}
