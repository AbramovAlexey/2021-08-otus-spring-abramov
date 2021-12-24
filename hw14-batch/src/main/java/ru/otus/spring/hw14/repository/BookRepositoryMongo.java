package ru.otus.spring.hw14.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import ru.otus.spring.hw14.model.BookMongo;

public interface BookRepositoryMongo extends MongoRepository<BookMongo, String> {
}
