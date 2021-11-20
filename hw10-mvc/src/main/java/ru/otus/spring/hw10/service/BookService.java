package ru.otus.spring.hw10.service;

import ru.otus.spring.hw10.model.Book;
import ru.otus.spring.hw10.model.Comment;

import java.util.List;

public interface BookService {

    Book create(String name, String authorName, String genreName);
    void update(String oldName, String newName);
    Book readByName(String name);
    List<Book> readAll();
    void deleteByName(String name);
    void updateDeleteGenre(String nameBook, String nameGenre);
    void updateAddGenre(String nameBook, String nameGenre);
    void updateDeleteAuthor(String nameBook, String nameAuthor);
    void updateAddAuthor(String nameBook, String nameAuthor);
    String addComment(String nameBook, String text);
    void deleteComment(String idComment);
    List<Comment> showAllComments(String nameBook);
    List<Book> findByAuthor(String name);
    List<Book> findByGenre(String name);
    Book readById(String id);

    void deleteById(String id);
    Book save(Book book);

}
