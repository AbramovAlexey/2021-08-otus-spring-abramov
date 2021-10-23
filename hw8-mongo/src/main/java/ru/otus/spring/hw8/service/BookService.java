package ru.otus.spring.hw8.service;

import ru.otus.spring.hw8.model.Book;

import java.util.List;

public interface BookService {

    String create(String name, String authorName, String genreName);
    void update(String oldName, String newName);
    Book readByName(String name);
    List<Book> readAll();
    void deleteByName(String name);
    void updateDeleteGenre(String nameBook, String nameGenre);
    void updateAddGenre(String nameBook, String nameGenre);
    void updateDeleteAuthor(String nameBook, String nameAuthor);
    void updateAddAuthor(String nameBook, String nameAuthor);

}
