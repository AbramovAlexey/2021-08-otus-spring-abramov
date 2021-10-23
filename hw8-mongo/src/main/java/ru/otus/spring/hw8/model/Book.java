package ru.otus.spring.hw8.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "books")
public class Book {

    public Book(String name, List<Author> authors, List<Genre> genres) {
        this(null, name, authors, genres);
    }

    @Id
    private String id;
    private String name;
    private List<Author> authors;
    private List<Genre> genres;

}
