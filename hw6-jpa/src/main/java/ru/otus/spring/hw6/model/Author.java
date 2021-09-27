package ru.otus.spring.hw6.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "Authors")
public class Author {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "full_name", nullable = false)
    private String fullName;

}
