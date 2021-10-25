package ru.otus.spring.hw8.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "comments")
public class Comment {

    public Comment(String bookId, String content) {
        this.bookId = bookId;
        this.content = content;
    }

    @Id
    String id;
    String bookId;
    String content;

}
