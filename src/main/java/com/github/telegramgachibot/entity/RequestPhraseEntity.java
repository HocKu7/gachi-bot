package com.github.telegramgachibot.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "request_phrase")
public class RequestPhraseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    private String phrase;

    @Column(name = "bot_answer_id", insertable = false, updatable = false)
    private Long botAnswerId;

    @ManyToOne
    @JoinColumn(name = "bot_answer_id")
    private BotAnswerEntity botAnswer;
}
