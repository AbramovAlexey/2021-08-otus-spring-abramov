package ru.otus.spring.hw7.service;

import ru.otus.spring.hw7.model.Book;

import java.util.List;

public interface BookService {

    long create(String name);
    void update(long id, String name);
    Book readById(long id);
    List<Book> readAll();
    void delete(long id);
    boolean addBookComment(long id, String content);
    boolean addBookGenre(long id, long genreId);
    boolean addBookAuthor(long id, long authorId);

    boolean deleteBookComment(long id, long commentId);
    boolean deleteBookGenre(long id, long genreId);
    boolean deleteBookAuthor(long id, long authorId);

}
