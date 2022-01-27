package ru.otus.spring.hw18.service;

import ru.otus.spring.hw18.model.Genre;

import java.util.List;

public interface GenreService {

    Genre readByName(String name);
    List<Genre> readAll();
    public Genre readOrCreate(String name);

}
