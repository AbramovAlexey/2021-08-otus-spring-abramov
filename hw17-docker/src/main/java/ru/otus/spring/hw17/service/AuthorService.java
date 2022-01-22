package ru.otus.spring.hw17.service;

import ru.otus.spring.hw17.model.Author;

import java.util.List;

public interface AuthorService {

    String create(String name);
    void update(String oldName, String newName);
    Author readByName(String name);
    List<Author> readAll();
    void deleteByName(String name);

    Author readOrCreate(String name);
}
