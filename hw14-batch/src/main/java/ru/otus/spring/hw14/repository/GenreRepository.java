package ru.otus.spring.hw14.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.otus.spring.hw14.model.Genre;


public interface GenreRepository extends JpaRepository<Genre, Long> {
}
