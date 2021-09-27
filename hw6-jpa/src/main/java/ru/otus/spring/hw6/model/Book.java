package ru.otus.spring.hw6.model;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table(name = "Books")
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "name", nullable = false)
    private String name;

    @ManyToMany(targetEntity = Author.class, fetch = FetchType.LAZY)
    @JoinTable(name = "books_authors", joinColumns = @JoinColumn(name = "book_id"),
            inverseJoinColumns = @JoinColumn(name = "author_id"))
    private List<Author> authors;

    @ManyToMany(targetEntity = Author.class, fetch = FetchType.LAZY)
    @JoinTable(name = "books_genres", joinColumns = @JoinColumn(name = "book_id"),
            inverseJoinColumns = @JoinColumn(name = "genre_id"))
    private List<Genre> genres;

    @OneToMany(targetEntity = BookComment.class, cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "book_id")
    private List<BookComment> bookComments;

}
