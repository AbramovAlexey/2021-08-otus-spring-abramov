package ru.otus.spring.hw5.dao;

import ru.otus.spring.hw5.domain.Genre;

import java.util.List;

public interface GenreDao {

    int count();

    long insert(Genre genre);

    Genre getById(long id);

    List<Genre> getAll();

    void deleteById(long id);

    void update(Genre genre);


}
