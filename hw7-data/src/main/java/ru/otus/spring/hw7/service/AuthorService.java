package ru.otus.spring.hw7.service;

import ru.otus.spring.hw7.model.Author;

import java.util.List;

public interface AuthorService {

    long create(String name);
    void update(long id, String fullName);
    Author readById(long id);
    List<Author> readAll();
    void delete(long id);

}
