package ru.otus.spring.hw6.repository;

import ru.otus.spring.hw6.model.BookComment;

import java.util.List;
import java.util.Optional;

public interface BookCommentRepository {

    long count();
    BookComment save(BookComment student);
    Optional<BookComment> findById(long id);
    List<BookComment> findAll();
    void deleteById(long id);
    
}
