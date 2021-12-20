package ru.otus.spring.hw13.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import ru.otus.spring.hw13.model.BlackListTokenItem;

import java.util.Optional;

public interface BlackListTokenItemRepository extends MongoRepository<BlackListTokenItem, String> {

    Optional<BlackListTokenItemRepository> findByToken(String token);

}
