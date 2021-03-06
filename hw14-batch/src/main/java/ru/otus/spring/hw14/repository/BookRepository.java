package ru.otus.spring.hw14.repository;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import ru.otus.spring.hw14.model.Book;

import java.util.List;


public interface BookRepository extends JpaRepository<Book, Long> {

    @EntityGraph(attributePaths = {
        "authors"
    })
    List<Book> findAll();

}
