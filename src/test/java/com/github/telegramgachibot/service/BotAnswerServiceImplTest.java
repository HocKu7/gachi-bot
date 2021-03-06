package com.github.telegramgachibot.service;

import java.io.IOException;

import com.github.telegramgachibot.TestConfig;
import com.github.telegramgachibot.TestUtil;
import com.github.telegramgachibot.entity.BotAnswerEntity;
import com.github.telegramgachibot.entity.constant.BotAnswerType;
import com.github.telegramgachibot.service.api.BotAnswerService;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.Import;
import org.springframework.web.multipart.MultipartFile;

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
        MultipartFile mockMultipartFile = TestUtil.getTestFile();

        //WHEN
        BotAnswerEntity actualEntity = botAnswerService.createByFile(mockMultipartFile, BotAnswerType.AUDIO);

        //THEN
        BotAnswerEntity findedEntity = testEntityManager.find(BotAnswerEntity.class, actualEntity.getId());
        assertThat(actualEntity.getId()).isEqualTo(findedEntity.getId());
    }

    @Test
    void getById() {

        //GIVEN
        MultipartFile mockMultipartFile = TestUtil.getTestFile();
        BotAnswerEntity expectedEntity = botAnswerService.createByFile(mockMultipartFile, BotAnswerType.AUDIO);

        //WHEN
        BotAnswerEntity actualEntity = botAnswerService.getById(expectedEntity.getId());

        //THEN
        assertThat(actualEntity).isEqualTo(expectedEntity);
    }
}
