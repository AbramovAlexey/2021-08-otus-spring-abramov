package ru.otus.spring.hw6.repository;

import ru.otus.spring.hw6.model.Book;

import java.util.List;
import java.util.Optional;

public interface BookRepository {

    long count();
    Book save(Book book);
    Optional<Book> findById(long id);
    List<Book> findAll();
    void deleteById(long id);

    List<Book> findWithGenreName(String genreName);
    List<Book> findWithAuthorName(String authorName);
    
}
