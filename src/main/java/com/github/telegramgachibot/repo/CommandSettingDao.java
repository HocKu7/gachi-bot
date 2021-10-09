package com.github.telegramgachibot.repo;

import com.github.telegramgachibot.entity.CommandSettingEntity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommandSettingDao extends JpaRepository<CommandSettingEntity, Long> {
}
