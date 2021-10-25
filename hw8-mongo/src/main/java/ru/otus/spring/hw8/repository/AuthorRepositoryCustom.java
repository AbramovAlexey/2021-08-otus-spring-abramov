package ru.otus.spring.hw8.repository;

import ru.otus.spring.hw8.model.Author;

import java.util.List;

public interface AuthorRepositoryCustom {

    List<Author> findAuthorsFromBooks();

}
