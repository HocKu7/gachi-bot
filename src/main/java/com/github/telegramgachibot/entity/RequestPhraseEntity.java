package com.github.telegramgachibot.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.ConstraintMode;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Indexed;
import org.hibernate.search.annotations.TermVector;

@Indexed
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "request_phrase")
public class RequestPhraseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Field(termVector = TermVector.YES)
    private String phrase;

    @Column(name = "bot_answer_id", insertable = false, updatable = false)
    private Long botAnswerId;

    @ManyToOne
    @JoinColumn(name = "bot_answer_id", referencedColumnName = "id",
            foreignKey = @ForeignKey(name = "bot_answer_id", value = ConstraintMode.CONSTRAINT))
    private BotAnswerEntity botAnswer;

    public RequestPhraseEntity(String phrase) {
        this.phrase = phrase;
    }
}
