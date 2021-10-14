package ru.otus.spring.hw7.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.otus.spring.hw7.model.Author;

import java.util.List;
import java.util.Optional;

public interface AuthorRepository extends JpaRepository<Author, Long> {
}
