package ru.otus.spring.hw7.service;

import ru.otus.spring.hw7.model.Genre;

import java.util.List;

public interface GenreService {

    long create(String name);
    void update(long id, String name);
    Genre readById(long id);
    List<Genre> readAll();
    void delete(long id);

}
