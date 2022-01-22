package ru.otus.spring.hw17.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "authors")
public class Author {

    public Author(String name) {
        this(null, name);
    }

    @Id
    private String id;
    private String name;

}
