package ru.otus.spring.hw18.service;

import ru.otus.spring.hw18.model.Book;
import ru.otus.spring.hw18.model.Comment;

import java.util.List;

public interface BookService {

    Book readByName(String name);
    List<Book> readAll();
    void updateDeleteAuthor(String nameBook, String nameAuthor);
    Book readById(String id);

    void deleteById(String id);
    Book save(Book book);

}
