package com.github.telegramgachibot.telegram.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.github.telegramgachibot.GachiBotUtil;
import com.github.telegramgachibot.entity.BotAnswerEntity;
import com.github.telegramgachibot.entity.RequestPhraseEntity;
import com.github.telegramgachibot.service.api.BotAnswerService;
import com.github.telegramgachibot.telegram.api.TelegramFinderAnswerService;

import org.apache.lucene.search.Query;
import org.hibernate.search.jpa.FullTextEntityManager;
import org.hibernate.search.jpa.FullTextQuery;
import org.hibernate.search.jpa.Search;
import org.hibernate.search.query.dsl.QueryBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

@Service
@RequiredArgsConstructor
@Slf4j
public class FullTextTelegramFinderAnswerServiceImpl implements TelegramFinderAnswerService {

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    private BotAnswerService botAnswerService;

    @Override
    @Transactional
    public Optional<BotAnswerEntity> findAnswer(List<String> args) {

        if (CollectionUtils.isEmpty(args)) {
            return getRandomAnswer(null);
        }
        String arg = String.join(" ", args);
        FullTextEntityManager fullTextEntityManager = Search.getFullTextEntityManager(entityManager);
        QueryBuilder queryBuilder = fullTextEntityManager.getSearchFactory()
                .buildQueryBuilder()
                .forEntity(RequestPhraseEntity.class).get();
        Query phrase1 = queryBuilder.keyword()
                .fuzzy()
                .withEditDistanceUpTo(1)
                .withPrefixLength(1)
                .onField("phrase")
                .matching(arg)
                .createQuery();
        FullTextQuery fullTextQuery = fullTextEntityManager.createFullTextQuery(phrase1, RequestPhraseEntity.class);
        List<RequestPhraseEntity> resultList = fullTextQuery.getResultList();

        if (resultList.isEmpty()) {
            return getRandomAnswer(arg);
        }

        int randomIndexBySize = GachiBotUtil.getRandomIndexBySize(resultList.size());
        RequestPhraseEntity requestPhraseEntity = resultList.get(randomIndexBySize);
        return Optional.ofNullable(requestPhraseEntity.getBotAnswer());
    }

    private Optional<BotAnswerEntity> getRandomAnswer(@Nullable String arg) {
        log.debug("По запросу: {} не удалось найти не одной фразы", arg);
        BotAnswerEntity randomOne = botAnswerService.getRandomOne();
        if (randomOne == null) {
            return Optional.empty();
        }
        return Optional.of(randomOne);
    }
}
