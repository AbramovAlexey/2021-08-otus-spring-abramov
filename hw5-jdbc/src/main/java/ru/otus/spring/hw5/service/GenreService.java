package ru.otus.spring.hw5.service;

import ru.otus.spring.hw5.domain.Genre;

import java.util.List;

public interface GenreService {

    long create(String name);
    void update(long id, String name);
    Genre readById(long id);
    List<Genre> readAll();
    void delete(long id);

}
