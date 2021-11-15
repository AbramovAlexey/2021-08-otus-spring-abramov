package ru.otus.spring.hw8.rest;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.otus.spring.hw8.model.Author;
import ru.otus.spring.hw8.service.AuthorService;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class AuthorController {

    private final AuthorService authorService;

    @GetMapping("api/authors")
    public List<Author> getAllAuthors() {
        return authorService.readAll();
    }

}
