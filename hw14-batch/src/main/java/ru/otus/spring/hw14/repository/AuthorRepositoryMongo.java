package ru.otus.spring.hw14.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import ru.otus.spring.hw14.model.AuthorMongo;


public interface AuthorRepositoryMongo extends MongoRepository<AuthorMongo, String> {
}
