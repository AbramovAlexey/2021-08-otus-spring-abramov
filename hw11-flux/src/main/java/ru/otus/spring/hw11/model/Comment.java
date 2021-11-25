package ru.otus.spring.hw11.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "comments")
public class Comment {

    public Comment(Book book, String content) {
        this.book = book;
        this.content = content;
    }

    @Id
    String id;
    @DBRef
    Book book;
    String content;

}
