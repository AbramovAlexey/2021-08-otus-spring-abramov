package ru.otus.spring.hw14.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import ru.otus.spring.hw14.model.BookMongo;

import java.util.List;

public interface BookRepositoryMongo extends MongoRepository<BookMongo, String> {

    BookMongo findByName(String name);
    void deleteByName(String name);
    List<BookMongo> findAllByAuthorsName(String name);
    List<BookMongo> findAllByGenresName(String name);
}
