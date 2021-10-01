package ru.otus.spring.hw6.repository;

import ru.otus.spring.hw6.model.Comment;

import java.util.List;
import java.util.Optional;

public interface CommentRepository {

    long count();
    Comment save(Comment student);
    Optional<Comment> findById(long id);
    List<Comment> findAll();
    void deleteById(long id);
    
}
