package com.github.telegramgachibot.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

import com.github.telegramgachibot.entity.RequestPhraseEntity;
import com.github.telegramgachibot.service.api.RequestPhraseService;

import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class RequestPhraseServiceImpl implements RequestPhraseService {

    @Override
    public List<RequestPhraseEntity> getPhraseEntityList(String fileName) {

        String[] strings = fileName.split(".mp3");
        String fileNameWithoutMp3 = strings[0];
        String[] stringsName = fileNameWithoutMp3.split(" ");
        List<RequestPhraseEntity> result = new ArrayList<>();
        for (String str : stringsName) {
            if (str.length() < 3) {
                continue;
            }
            result.add(new RequestPhraseEntity(str));
        }

        return result;
    }
}
