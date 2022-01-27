package ru.otus.spring.hw18.service;

import ru.otus.spring.hw18.model.Book;
import ru.otus.spring.hw18.model.Comment;

import java.util.List;

public interface BookService {

    Book readByName(String name);
    List<Book> readAll();
    Book readById(String id);
    void deleteById(String id);
    Book save(Book book);

}
