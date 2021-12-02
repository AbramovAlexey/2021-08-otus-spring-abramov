package ru.otus.spring.hw11.dto;

import lombok.*;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class BookDto {

    private String id;
    private String name;
    private String authors;
    private String genres;

}
