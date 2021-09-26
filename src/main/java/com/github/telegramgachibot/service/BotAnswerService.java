package com.github.telegramgachibot.service;

import com.github.telegramgachibot.entity.BotAnswerEntity;

import org.springframework.web.multipart.MultipartFile;

public interface BotAnswerService {

    BotAnswerEntity createByFile(MultipartFile multipartFile);

    BotAnswerEntity getById(Long id);
}
