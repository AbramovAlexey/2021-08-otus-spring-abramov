package ru.otus.spring.hw8.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Component;
import ru.otus.spring.hw8.model.Author;

@Component
public interface AuthorRepository extends MongoRepository<Author, String>, AuthorRepositoryCustom {

    Author findByName(String name);
    void deleteByName(String name);

}
