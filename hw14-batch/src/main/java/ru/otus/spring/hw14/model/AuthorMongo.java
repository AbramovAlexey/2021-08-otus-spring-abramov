package ru.otus.spring.hw14.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "authors")
public class AuthorMongo {

    public AuthorMongo(String name) {
        this(null, name);
    }

    @Id
    private String id;
    private String name;

}
