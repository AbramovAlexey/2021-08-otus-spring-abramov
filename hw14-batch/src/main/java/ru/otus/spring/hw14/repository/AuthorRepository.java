package ru.otus.spring.hw14.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.otus.spring.hw14.model.Author;


public interface AuthorRepository extends JpaRepository<Author, Long> {
}
