package com.github.telegramgachibot.rest;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.net.URI;
import java.security.Principal;

import com.github.telegramgachibot.dto.BotAnswerDto;
import com.github.telegramgachibot.entity.AudioBotAnswerEntity;
import com.github.telegramgachibot.service.api.AudioBotAnswerService;

import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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

    private final AudioBotAnswerService botAnswerService;

    private final ModelMapper modelMapper;

    @PostMapping("/upload")
    public ResponseEntity<Void> uploadAnswer(@RequestParam("files") MultipartFile[] multipartFiles) {

        for (MultipartFile multipartFile : multipartFiles) {

            log.debug("Получен файл с именем {} ", multipartFile.getName());
            botAnswerService.createByFile(multipartFile);
        }

        return ResponseEntity.created(URI.create("")).build();
    }

    @GetMapping("/{id}")
    public BotAnswerDto getById(@PathVariable Long id, Principal principal) {

        AudioBotAnswerEntity byId = botAnswerService.getById(id);
        BotAnswerDto dto = modelMapper.map(byId, BotAnswerDto.class);
        return dto;
    }
}
