package com.github.telegramgachibot.repo;

import java.util.List;

import com.github.telegramgachibot.entity.BotAnswerEntity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BotAnswerDao extends JpaRepository<BotAnswerEntity, Long> {

    List<BotAnswerEntity> findByFileName(String fileName);
}
