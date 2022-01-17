package ru.otus.spring.hw17.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import ru.otus.spring.hw17.model.BlackListTokenItem;

import java.util.Optional;

public interface BlackListTokenItemRepository extends MongoRepository<BlackListTokenItem, String> {

    Optional<BlackListTokenItemRepository> findByToken(String token);

}
