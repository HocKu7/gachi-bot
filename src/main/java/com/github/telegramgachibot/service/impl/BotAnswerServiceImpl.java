package com.github.telegramgachibot.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.List;

import javax.transaction.Transactional;

import com.github.telegramgachibot.entity.AudioBotAnswerEntity;
import com.github.telegramgachibot.entity.BotAnswerEntity;
import com.github.telegramgachibot.entity.constant.BotAnswerType;
import com.github.telegramgachibot.repo.BotAnswerDao;
import com.github.telegramgachibot.service.api.BotAnswerService;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Slf4j
@Service
@RequiredArgsConstructor
public class BotAnswerServiceImpl implements BotAnswerService {

    private final BotAnswerDao botAnswerDao;

    @SneakyThrows
    @Override
    @Transactional
    public BotAnswerEntity createByFile(MultipartFile multipartFile,
                                        BotAnswerType botAnswerType) {
        BotAnswerEntity botAnswerEntity = null;
        if (BotAnswerType.AUDIO.equals(botAnswerType)) {
            botAnswerEntity = AudioBotAnswerEntity.builder()
                    .content(multipartFile.getBytes())
                    .fileName(multipartFile.getName())
                    .size(multipartFile.getSize())
                    .botAnswerType(botAnswerType)
                    .build();
        } else {
            throw new NotImplementedException();
        }
//        botAnswerEntity = BotAnswerEntity.builder()
//                .content(multipartFile.getBytes())
//                .fileName(multipartFile.getName())
//                .size(multipartFile.getSize())
//                .botAnswerType(botAnswerType)
//                .build();
        botAnswerDao.save(botAnswerEntity);
        log.debug("Сущность BotAnswerEntity c id = {} успешно сохранена", botAnswerEntity.getId());
        return botAnswerEntity;
    }

    @Override
    @Transactional
    public BotAnswerEntity getById(Long id) {
        return botAnswerDao.findById(id).orElseThrow(() ->
                new RuntimeException("Сущность не найдена"));
    }

    @Override
    public List<BotAnswerEntity> findByFileName(String text) {

        return botAnswerDao.findByFileName(text);
    }
}
