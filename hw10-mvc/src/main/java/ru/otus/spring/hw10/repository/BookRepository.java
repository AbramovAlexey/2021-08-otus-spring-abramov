package ru.otus.spring.hw10.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Component;
import ru.otus.spring.hw10.model.Book;

import java.util.List;

@Component
public interface BookRepository extends MongoRepository<Book, String> {

    Book findByName(String name);
    void deleteByName(String name);
    List<Book> findAllByAuthorsName(String name);
    List<Book> findAllByGenresName(String name);
}
