package com.github.telegramgachibot.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import com.github.telegramgachibot.entity.constant.BotAnswerType;

@Entity
@Data
@NoArgsConstructor
@DiscriminatorValue(value = BotAnswerType.Values.AUDIO)
@SuperBuilder
public class AudioBotAnswerEntity extends BotAnswerEntity {

}
