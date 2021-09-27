package ru.otus.spring.hw6.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "Genres")
public class Genre {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "name", nullable = false)
    private String name;

}
