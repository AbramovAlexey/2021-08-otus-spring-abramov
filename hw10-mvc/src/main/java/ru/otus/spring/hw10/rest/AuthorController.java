package ru.otus.spring.hw10.rest;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.otus.spring.hw10.dto.AuthorDto;
import ru.otus.spring.hw10.model.Author;
import ru.otus.spring.hw10.service.AuthorService;
import ru.otus.spring.hw10.service.DtoConverter;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class AuthorController {

    private final DtoConverter dtoConverter;
    private final AuthorService authorService;

    @SneakyThrows
    @GetMapping("api/authors")
    public List<AuthorDto> getAllAuthors() {
        Thread.sleep(1000);
        return dtoConverter.authorsToDto(authorService.readAll());
    }

}
