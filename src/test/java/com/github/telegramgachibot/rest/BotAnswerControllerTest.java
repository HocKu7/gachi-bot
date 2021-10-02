package com.github.telegramgachibot.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.telegramgachibot.AbstractIntegrationTest;
import com.github.telegramgachibot.TestUtil;
import com.github.telegramgachibot.dto.BotAnswerDto;
import com.github.telegramgachibot.entity.BotAnswerEntity;
import com.github.telegramgachibot.entity.constant.BotAnswerType;
import com.github.telegramgachibot.service.api.BotAnswerService;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.multipart.MultipartFile;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
class BotAnswerControllerTest extends AbstractIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private BotAnswerService botAnswerService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void uploadAnswer() throws Exception {


    }

    @Test
    void getById() throws Exception {

        //GIVEN
        MultipartFile testFile = TestUtil.getTestFile();
        BotAnswerEntity expected = botAnswerService.createByFile(testFile, BotAnswerType.AUDIO);

        //WHEN
        String body = mockMvc.perform(get("/bot-answer/" + expected.getId()))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"))
                .andReturn().getResponse().getContentAsString();

        BotAnswerDto botAnswerDto = objectMapper.readValue(body, BotAnswerDto.class);

        //THEN
        assertThat(botAnswerDto.getId()).isEqualTo(expected.getId());
        assertThat(botAnswerDto.getBotAnswerType()).isEqualTo(expected.getBotAnswerType());
        assertThat(botAnswerDto.getFileName()).isEqualTo(expected.getFileName());
        assertThat(botAnswerDto.getSize()).isEqualTo(expected.getSize());

    }
}
