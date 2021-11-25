package ru.otus.spring.hw11.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;

@Getter
@Setter
@AllArgsConstructor
public class BookDto {

    private String id;
    @NotEmpty(message = "Field name must be provided")
    private String name;
    @NotEmpty(message = "Field authors must be provided")
    private String authors;
    @NotEmpty(message = "Field genres must be provided")
    private String genres;

}
