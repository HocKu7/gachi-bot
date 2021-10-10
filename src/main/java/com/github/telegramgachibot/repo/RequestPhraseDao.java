package com.github.telegramgachibot.repo;

import com.github.telegramgachibot.entity.RequestPhraseEntity;

import org.springframework.data.jpa.repository.JpaRepository;

public interface RequestPhraseDao extends JpaRepository<RequestPhraseEntity, Long> {
}
