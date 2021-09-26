package com.github.telegramgachibot.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;

import com.github.telegramgachibot.entity.BotAnswerEntity;
import com.github.telegramgachibot.repo.BotAnswerRepository;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Slf4j
@Service
@RequiredArgsConstructor
public class BotAnswerServiceImpl implements BotAnswerService {

    private final BotAnswerRepository botAnswerRepository;

    @Override
    public BotAnswerEntity createByFile(MultipartFile multipartFile) {

        BotAnswerEntity botAnswerEntity = null;
        try {
            botAnswerEntity = BotAnswerEntity.builder()
                    .content(multipartFile.getBytes())
                    .build();
            botAnswerRepository.save(botAnswerEntity);
            log.debug("Сущность BotAnswerEntity c id = {} успешно сохранена", botAnswerEntity.getId());
        } catch (IOException e) {
            log.error("При попытке сохранить файл fileName = {} произошла ошибка", multipartFile.getOriginalFilename());
        }
        return botAnswerEntity;
    }

    @Override
    public BotAnswerEntity getById(Long id) {
        return botAnswerRepository.getById(id);
    }
}
