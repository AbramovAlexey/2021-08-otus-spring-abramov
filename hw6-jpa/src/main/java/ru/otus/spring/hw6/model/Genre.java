package ru.otus.spring.hw6.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table(name = "Genres")
@AllArgsConstructor
@NoArgsConstructor
public class Genre {

    public Genre(long id, String name) {
        this(id, name, null);
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "name", nullable = false)
    private String name;

    @ManyToMany(mappedBy = "genres", fetch = FetchType.LAZY)
    @ToString.Exclude
    private List<Book> books;

}
