package ru.otus.spring.hw8.service;

import ru.otus.spring.hw8.model.Author;

import java.util.List;

public interface AuthorService {

    String create(String name);
    void update(String oldName, String newName);
    Author readByName(String name);
    List<Author> readAll();
    void deleteByName(String name);
    boolean checkAuthorsConsistency();

}
