package ru.otus.spring.hw18.service;

import ru.otus.spring.hw18.model.Author;

import java.util.List;

public interface AuthorService {
    Author readByName(String name);
    List<Author> readAll();
    Author readOrCreate(String name);
}
