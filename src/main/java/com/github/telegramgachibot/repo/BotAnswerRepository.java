package com.github.telegramgachibot.repo;

import com.github.telegramgachibot.entity.BotAnswerEntity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BotAnswerRepository extends JpaRepository<BotAnswerEntity, Long> {
}
