package ru.otus.spring.hw7.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.otus.spring.hw7.model.Author;
import ru.otus.spring.hw7.model.Genre;

import java.util.List;
import java.util.Optional;

public interface GenreRepository extends JpaRepository<Genre, Long> {
}
