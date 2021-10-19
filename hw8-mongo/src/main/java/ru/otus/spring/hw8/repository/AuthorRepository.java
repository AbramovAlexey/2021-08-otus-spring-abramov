package ru.otus.spring.hw8.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import ru.otus.spring.hw8.model.Author;

public interface AuthorRepository extends MongoRepository<Author, String> {
}
