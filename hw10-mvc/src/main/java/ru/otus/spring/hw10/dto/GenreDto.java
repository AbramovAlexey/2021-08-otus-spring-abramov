package ru.otus.spring.hw10.dto;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@AllArgsConstructor
public class GenreDto {

    private String id;
    private String name;

}
