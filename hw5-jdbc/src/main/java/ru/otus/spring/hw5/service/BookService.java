package ru.otus.spring.hw5.service;

import ru.otus.spring.hw5.domain.Book;

import java.util.List;

public interface BookService {

    long create(String name, long authorId, long genreId);
    boolean update(long id, String name, long authorId, long genreId);
    Book readById(long id);
    List<Book> readAll();
    void delete(long id);

}
