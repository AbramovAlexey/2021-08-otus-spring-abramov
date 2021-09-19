package ru.otus.spring.hw5.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
public class Author {

    private final long id;
    private final String fullName;

}
