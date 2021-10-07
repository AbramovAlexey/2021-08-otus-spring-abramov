package ru.otus.spring.hw7.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import ru.otus.spring.hw7.model.Author;
import ru.otus.spring.hw7.model.Book;

import java.util.List;
import java.util.Optional;

public interface BookRepository extends JpaRepository<Book, Long> {

    List<Book> findByGenresName(String name);
    List<Book> findByAuthorsFullName(String fullName);

}
