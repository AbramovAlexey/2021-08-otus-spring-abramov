package ru.otus.spring.hw18.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import ru.otus.spring.hw18.model.BlackListTokenItem;

import java.util.Optional;

public interface BlackListTokenItemRepository extends MongoRepository<BlackListTokenItem, String> {

    Optional<BlackListTokenItem> findByToken(String token);

}
