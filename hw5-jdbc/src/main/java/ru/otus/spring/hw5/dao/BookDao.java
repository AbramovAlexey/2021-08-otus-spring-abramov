package ru.otus.spring.hw5.dao;


import ru.otus.spring.hw5.domain.Book;

import java.util.List;

public interface BookDao {
    
    int count();

    long insert(Book book);

    Book getById(long id);

    List<Book> getAll();

    void deleteById(long id);

    void update(Book book);
    
}
