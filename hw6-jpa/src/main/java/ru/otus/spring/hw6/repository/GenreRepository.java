package ru.otus.spring.hw6.repository;

import ru.otus.spring.hw6.model.Genre;

import java.util.List;
import java.util.Optional;

public interface GenreRepository {

    long count();
    Genre save(Genre genre);
    Optional<Genre> findById(long id);
    List<Genre> findAll();
    void deleteById(long id);
    
}
