package com.github.telegramgachibot.service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

import com.github.telegramgachibot.TestConfig;
import com.github.telegramgachibot.entity.BotAnswerEntity;
import com.github.telegramgachibot.entity.constant.BotAnswerType;
import com.github.telegramgachibot.service.api.BotAnswerService;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.Import;
import org.springframework.mock.web.MockMultipartFile;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Import(TestConfig.class)
class BotAnswerServiceImplTest {

    @Autowired
    private BotAnswerService botAnswerService;

    @Autowired
    private TestEntityManager testEntityManager;

    @Test
    void createByFile_simpleSaving_savedToDb() throws IOException {

        //GIVEN
        File file = new File("src/test/resources/test.mp3");
        byte[] allBytes = Files.readAllBytes(file.toPath());
        MockMultipartFile mockMultipartFile = new MockMultipartFile("test.file", allBytes);

        //WHEN
        BotAnswerEntity actualEntity = botAnswerService.createByFile(mockMultipartFile, BotAnswerType.AUDIO);

        //THEN
        BotAnswerEntity findedEntity = testEntityManager.find(BotAnswerEntity.class, actualEntity.getId());
        assertThat(actualEntity.getId()).isEqualTo(findedEntity.getId());
    }
}
