package ru.otus.spring.hw6.repository;

import ru.otus.spring.hw6.model.Author;

import java.util.List;
import java.util.Optional;

public interface AuthorRepository {

    long count();
    Author save(Author student);
    Optional<Author> findById(long id);
    List<Author> findAll();
    void deleteById(long id);
    
}
