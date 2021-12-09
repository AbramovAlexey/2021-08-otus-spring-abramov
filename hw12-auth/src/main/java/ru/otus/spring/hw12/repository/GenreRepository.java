package ru.otus.spring.hw12.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Component;
import ru.otus.spring.hw12.model.Genre;

@Component
public interface GenreRepository extends MongoRepository<Genre, String> {

    Genre findByName(String name);
    void deleteByName(String name);

}
