package com.github.telegramgachibot.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import com.github.telegramgachibot.entity.constant.BotAnswerType;

@Entity
@NoArgsConstructor
@Data
@DiscriminatorValue(value = BotAnswerType.Values.IMAGE)
public class ImageBotAnswerEntity extends BotAnswerEntity {
}
