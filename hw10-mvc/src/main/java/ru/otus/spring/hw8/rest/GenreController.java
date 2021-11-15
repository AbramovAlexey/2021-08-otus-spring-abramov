package ru.otus.spring.hw8.rest;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.otus.spring.hw8.model.Genre;
import ru.otus.spring.hw8.service.GenreService;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class GenreController {

    private final GenreService genreService;

    @GetMapping("api/genres")
    public List<Genre> getAllAuthors() {
        return genreService.readAll();
    }
}
