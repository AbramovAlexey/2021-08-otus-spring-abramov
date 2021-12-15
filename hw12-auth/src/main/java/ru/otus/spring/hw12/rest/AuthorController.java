package ru.otus.spring.hw12.rest;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.otus.spring.hw12.dto.AuthorDto;
import ru.otus.spring.hw12.service.AuthorService;
import ru.otus.spring.hw12.service.DtoConverter;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class AuthorController {

    private final DtoConverter dtoConverter;
    private final AuthorService authorService;

    @GetMapping("api/authors")
    public List<AuthorDto> getAllAuthors() {
        return dtoConverter.authorsToDto(authorService.readAll());
    }

}
