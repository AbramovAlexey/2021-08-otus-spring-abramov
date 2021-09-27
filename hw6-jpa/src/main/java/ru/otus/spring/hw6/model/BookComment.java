package ru.otus.spring.hw6.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "book_comments")
public class BookComment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "content", nullable = false)
    private String content;

}
