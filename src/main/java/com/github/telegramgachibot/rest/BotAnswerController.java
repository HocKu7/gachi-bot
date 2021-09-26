package com.github.telegramgachibot.rest;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.net.URI;

import com.github.telegramgachibot.service.BotAnswerService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/bot-answer")
public class BotAnswerController {

    private final BotAnswerService botAnswerService;

    @PostMapping("/upload")
    public ResponseEntity<Void> uploadAnswer(@RequestParam("files") MultipartFile[] multipartFiles) {

        for (MultipartFile multipartFile : multipartFiles) {

            log.debug("Получен файл с именем {} ", multipartFile.getName());
            botAnswerService.createByFile(multipartFile);
        }

        return ResponseEntity.created(URI.create("")).build();
    }
}
