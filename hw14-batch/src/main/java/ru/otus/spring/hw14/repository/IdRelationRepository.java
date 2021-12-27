package ru.otus.spring.hw14.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import ru.otus.spring.hw14.model.IdRelation;

import java.util.Optional;

public interface IdRelationRepository extends MongoRepository<IdRelation, String> {
    Optional<IdRelation> findBySqlIdAndType(long sqlId, String type);
}
