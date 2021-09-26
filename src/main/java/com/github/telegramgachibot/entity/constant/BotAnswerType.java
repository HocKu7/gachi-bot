package com.github.telegramgachibot.entity.constant;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum BotAnswerType {

    TEXT(Values.TEXT), AUDIO(Values.AUDIO), IMAGE(Values.IMAGE), NONE(Values.NONE);

    private final String name;

    public static class Values {
        public static final String TEXT = "TEXT";

        public static final String AUDIO = "AUDIO";

        public static final String IMAGE = "IMAGE";

        public static final String NONE = "NONE";
    }
}
