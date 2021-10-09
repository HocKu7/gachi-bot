package com.github.telegramgachibot.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

import com.github.telegramgachibot.entity.CommandSettingEntity;
import com.github.telegramgachibot.repo.CommandSettingDao;
import com.github.telegramgachibot.service.api.CommandSettingService;

import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class CommandSettingServiceImpl implements CommandSettingService {

    private final CommandSettingDao commandSettingDao;

    @Override
    public CommandSettingEntity save(CommandSettingEntity commandSettingEntity) {
        return commandSettingDao.save(commandSettingEntity);
    }

    @Override
    public Integer getCurrentOffset() {
        List<CommandSettingEntity> commandSettingEntities = commandSettingDao.findAll();
        if (commandSettingEntities.size() != 1) {
            throw new RuntimeException("Из бд получено команд не равное единице");
        }
        CommandSettingEntity commandSettingEntity = commandSettingEntities.get(0);
        return commandSettingEntity.getLastPointerOffset();
    }
}
