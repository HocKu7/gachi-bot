package com.github.telegramgachibot.repo;

import com.github.telegramgachibot.entity.AudioBotAnswerEntity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AudioBotAnswerRepository extends JpaRepository<AudioBotAnswerEntity, Long> {
}
