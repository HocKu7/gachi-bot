package com.github.telegramgachibot.service.api;

import com.github.telegramgachibot.entity.BotAnswerEntity;
import com.github.telegramgachibot.entity.constant.BotAnswerType;

import org.springframework.web.multipart.MultipartFile;

public interface BotAnswerService {

    BotAnswerEntity createByFile(MultipartFile multipartFile,
                                 BotAnswerType botAnswerType);

    BotAnswerEntity getById(Long id);
}
