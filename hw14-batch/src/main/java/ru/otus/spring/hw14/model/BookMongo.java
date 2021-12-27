package ru.otus.spring.hw14.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "books")
public class BookMongo {

    public BookMongo(String name, List<AuthorMongo> authors, List<GenreMongo> genres) {
        this(null, name, authors, genres);
    }

    @Id
    private String id;
    private String name;
    @DBRef
    private List<AuthorMongo> authors;
    @DBRef
    private List<GenreMongo> genres;

}
