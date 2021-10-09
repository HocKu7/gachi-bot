package com.github.telegramgachibot.service.telegram;

import com.github.telegramgachibot.AbstractIntegrationTest;
import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.request.GetUpdates;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.mockito.BDDMockito.given;

class TelegramUpdateContextServiceImplTest extends AbstractIntegrationTest {

    @MockBean
    private TelegramBot telegramBot;

    @Test
    void updateContext() {

        GetUpdates getUpdates = new GetUpdates()
                .limit(100)
                .offset(0)
                .timeout(0);
        given(telegramBot.execute(getUpdates)).willReturn()
    }
}
