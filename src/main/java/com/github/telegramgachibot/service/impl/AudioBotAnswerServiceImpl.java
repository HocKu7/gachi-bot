package com.github.telegramgachibot.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

import javax.transaction.Transactional;

import com.github.telegramgachibot.entity.AudioBotAnswerEntity;
import com.github.telegramgachibot.entity.BotAnswerEntity;
import com.github.telegramgachibot.entity.RequestPhraseEntity;
import com.github.telegramgachibot.repo.AudioBotAnswerDao;
import com.github.telegramgachibot.service.api.AudioBotAnswerService;
import com.github.telegramgachibot.service.api.RequestPhraseService;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Slf4j
@Service
@RequiredArgsConstructor
public class AudioBotAnswerServiceImpl implements AudioBotAnswerService {

    private final AudioBotAnswerDao botAnswerRepository;

    private final RequestPhraseService requestPhraseService;

    @SneakyThrows
    @Override
    @Transactional
    public BotAnswerEntity createByFile(MultipartFile multipartFile) {

        String originalFilename = multipartFile.getOriginalFilename();
        List<RequestPhraseEntity> phraseEntityList = requestPhraseService.getPhraseEntityList(originalFilename);
        AudioBotAnswerEntity botAnswerEntity = AudioBotAnswerEntity.builder()
                .content(multipartFile.getBytes())
                .fileName(originalFilename)
                .size(multipartFile.getSize())
                .requestPhrases(phraseEntityList)
                .build();
        phraseEntityList.forEach(requestPhraseEntity -> requestPhraseEntity.setBotAnswer(botAnswerEntity));
        botAnswerRepository.save(botAnswerEntity);
        log.debug("Сущность BotAnswerEntity c id = {} успешно сохранена", botAnswerEntity.getId());
        return botAnswerEntity;
    }

    @Override
    @Transactional
    public AudioBotAnswerEntity getById(Long id) {
        AudioBotAnswerEntity entity = botAnswerRepository.getById(id);
        return entity;
    }
}
