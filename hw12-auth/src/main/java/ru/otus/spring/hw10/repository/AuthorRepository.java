package ru.otus.spring.hw10.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Component;
import ru.otus.spring.hw10.model.Author;

@Component
public interface AuthorRepository extends MongoRepository<Author, String> {

    Author findByName(String name);
    void deleteByName(String name);

}
