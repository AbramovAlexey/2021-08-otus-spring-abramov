package ru.otus.spring.hw12.service;

import ru.otus.spring.hw12.model.Genre;

import java.util.List;

public interface GenreService {

    String create(String name);
    void update(String oldName, String newName);
    Genre readByName(String name);
    List<Genre> readAll();
    void deleteByName(String name);
    public Genre readOrCreate(String name);
}
