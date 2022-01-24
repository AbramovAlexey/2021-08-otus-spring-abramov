package ru.otus.spring.hw18.rest;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.http.HttpStatus;

import java.util.List;

@RequiredArgsConstructor
@Getter
@Setter
public class ApiError {

    private final int status;
    private final List<String> errors;

}
