package com.github.telegramgachibot.service.api;

import java.util.List;

import com.github.telegramgachibot.entity.RequestPhraseEntity;

public interface RequestPhraseService {

    List<RequestPhraseEntity> getPhraseEntityList(String fileName);
}
