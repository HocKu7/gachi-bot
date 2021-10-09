package com.github.telegramgachibot.service.api;

import com.github.telegramgachibot.entity.CommandSettingEntity;

public interface CommandSettingService {

    CommandSettingEntity save(CommandSettingEntity commandSettingEntity);

    Integer getCurrentOffset();
}
