package com.github.telegramgachibot.telegram.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.Optional;
import java.util.Random;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.github.telegramgachibot.entity.BotAnswerEntity;
import com.github.telegramgachibot.entity.RequestPhraseEntity;
import com.github.telegramgachibot.telegram.api.TelegramFinderAnswerService;

import org.apache.lucene.search.Query;
import org.hibernate.search.jpa.FullTextEntityManager;
import org.hibernate.search.jpa.FullTextQuery;
import org.hibernate.search.jpa.Search;
import org.hibernate.search.query.dsl.QueryBuilder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Slf4j
public class FullTextTelegramFinderAnswerServiceImpl implements TelegramFinderAnswerService {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    @Transactional
    public Optional<BotAnswerEntity> findAnswer(List<String> args) {

        String arg = args.get(0);
        if (arg == null) {
            arg = "boss";
        }
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
            log.debug("По запросу: {} не удалось найти не одной фразы", arg);
        }
        int index = new Random().nextInt() % resultList.size();
        return Optional.empty();
    }
}
