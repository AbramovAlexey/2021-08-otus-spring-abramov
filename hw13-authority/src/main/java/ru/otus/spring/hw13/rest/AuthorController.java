package ru.otus.spring.hw13.rest;

import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.otus.spring.hw13.dto.AuthorDto;
import ru.otus.spring.hw13.service.AuthorService;
import ru.otus.spring.hw13.service.DtoConverter;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class AuthorController {

    private final DtoConverter dtoConverter;
    private final AuthorService authorService;

    @GetMapping("api/authors")
    @PreAuthorize("hasRole('USER') or hasRole('MANAGER')")
    public List<AuthorDto> getAllAuthors() {
        return dtoConverter.authorsToDto(authorService.readAll());
    }

}
