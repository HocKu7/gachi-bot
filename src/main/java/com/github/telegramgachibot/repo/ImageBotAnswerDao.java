package com.github.telegramgachibot.repo;

import com.github.telegramgachibot.entity.ImageBotAnswerEntity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ImageBotAnswerDao extends JpaRepository<ImageBotAnswerEntity, Long> {
}
