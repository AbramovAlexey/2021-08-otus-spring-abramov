package ru.otus.spring.hw14.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import ru.otus.spring.hw14.model.GenreMongo;


public interface GenreRepositoryMongo extends MongoRepository<GenreMongo, String> {

    GenreMongo findByName(String name);
    void deleteByName(String name);

}
