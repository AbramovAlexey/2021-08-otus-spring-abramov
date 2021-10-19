package ru.otus.spring.hw8.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@AllArgsConstructor
@Document(collection = "authors")
public class Author {

    public Author(String name) {
        this(null, name);
    }

    @Id
    private String id;
    private String name;

}
