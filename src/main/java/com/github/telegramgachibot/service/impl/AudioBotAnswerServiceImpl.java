package com.github.telegramgachibot.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;

import javax.transaction.Transactional;

import com.github.telegramgachibot.entity.AudioBotAnswerEntity;
import com.github.telegramgachibot.entity.BotAnswerEntity;
import com.github.telegramgachibot.repo.AudioBotAnswerRepository;
import com.github.telegramgachibot.service.api.AudioBotAnswerService;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Slf4j
@Service
@RequiredArgsConstructor
public class AudioBotAnswerServiceImpl implements AudioBotAnswerService {

    private final AudioBotAnswerRepository botAnswerRepository;

    @Override
    @Transactional
    public BotAnswerEntity createByFile(MultipartFile multipartFile) {

        AudioBotAnswerEntity botAnswerEntity = null;
        try {
            botAnswerEntity = AudioBotAnswerEntity.builder()
                    .content(multipartFile.getBytes())
                    .fileName(multipartFile.getOriginalFilename())
                    .size(multipartFile.getSize())
                    .build();
            botAnswerRepository.save(botAnswerEntity);
            log.debug("Сущность BotAnswerEntity c id = {} успешно сохранена", botAnswerEntity.getId());
        } catch (IOException e) {
            log.error("При попытке сохранить файл fileName = {} произошла ошибка", multipartFile.getOriginalFilename());
        }
        return botAnswerEntity;
    }

    @Override
    @Transactional
    public AudioBotAnswerEntity getById(Long id) {
        AudioBotAnswerEntity entity = botAnswerRepository.getById(id);
        return entity;
    }
}
