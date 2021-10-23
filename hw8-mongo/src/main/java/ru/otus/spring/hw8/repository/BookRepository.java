package ru.otus.spring.hw8.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Component;
import ru.otus.spring.hw8.model.Book;

@Component
public interface BookRepository extends MongoRepository<Book, String> {

    Book findByName(String name);
    void deleteByName(String name);

}
