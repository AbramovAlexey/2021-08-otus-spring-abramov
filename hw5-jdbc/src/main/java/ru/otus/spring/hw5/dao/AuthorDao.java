package ru.otus.spring.hw5.dao;

import ru.otus.spring.hw5.domain.Author;

import java.util.List;

public interface AuthorDao {

    int count();

    long insert(Author author);

    Author getById(long id);

    List<Author> getAll();

    void deleteById(long id);

    void update(Author author);

}
