package ru.otus.spring.hw14.job.processors;

public interface RelationCacheService {

    String getRelationId(Long sqlId, Class _class);

}