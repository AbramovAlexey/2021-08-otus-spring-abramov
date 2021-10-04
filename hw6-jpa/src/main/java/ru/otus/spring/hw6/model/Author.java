package ru.otus.spring.hw6.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "Authors")
public class Author {

    public Author(long id, String fullName) {
        this(id, fullName, null);
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "full_name", nullable = false)
    private String fullName;

    @ManyToMany(mappedBy = "authors", fetch = FetchType.LAZY)
    @ToString.Exclude
    private List<Book> books;

}
