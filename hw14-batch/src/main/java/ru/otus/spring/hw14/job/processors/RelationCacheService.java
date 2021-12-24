package ru.otus.spring.hw14.job.processors;

import org.springframework.stereotype.Service;

public interface RelationCacheService {

    String getRelationId(Long sqlId, Class _class);

}