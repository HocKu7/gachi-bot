package com.github.telegramgachibot.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.time.OffsetDateTime;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.github.telegramgachibot.entity.constant.BotAnswerType;

import org.hibernate.annotations.CreationTimestamp;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Table(name = "bot_answer")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "answer_type")
public class BotAnswerEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    private byte[] content;

    private long size;

    private String fileName;

    @Enumerated(EnumType.STRING)
    @Column(name = "answer_type", insertable = false, updatable = false)
    private BotAnswerType botAnswerType;

    @CreationTimestamp
    @Column(nullable = false, updatable = false)
    private OffsetDateTime createDttm;

    @OneToMany(mappedBy = "botAnswer")
    private List<RequestPhraseEntity> requestPhrases;

}
