package ru.otus.spring.hw5.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
public class Book {

    private final long id;
    private final String name;
    private final Author author;
    private final Genre genre;

}
