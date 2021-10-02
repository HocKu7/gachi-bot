package com.github.telegramgachibot.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

import com.github.telegramgachibot.entity.constant.BotAnswerType;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BotAnswerDto {

    private Long id;

    private byte[] content;

    private long size;

    private String fileName;

    private BotAnswerType botAnswerType;

//    @JsonSerialize(using = InstantSerializer.class)
//    @JsonDeserialize(using = InstantDese.class)
    private Instant createDttm;
}
