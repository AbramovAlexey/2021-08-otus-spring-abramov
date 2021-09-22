package ru.otus.spring.hw5.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Book {

    public Book(long id, String name, long authorId, long genreId) {
        this.id = id;
        this.name = name;
        this.author = new Author(authorId, null);
        this.genre = new Genre(genreId, null);
    }

    public Book(String name, long authorId, long genreId) {
        this(0, name, authorId, genreId);
    }

    private final long id;
    private final String name;
    private final Author author;
    private final Genre genre;

}
