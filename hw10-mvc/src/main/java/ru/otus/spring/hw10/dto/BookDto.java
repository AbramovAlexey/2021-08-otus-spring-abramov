package ru.otus.spring.hw10.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class BookDto {

    private String id;
    private String name;
    private String authors;
    private String genres;

}
