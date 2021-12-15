package ru.otus.spring.hw12.rest;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.otus.spring.hw12.dto.GenreDto;
import ru.otus.spring.hw12.service.DtoConverter;
import ru.otus.spring.hw12.service.GenreService;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class GenreController {

    private final GenreService genreService;
    private final DtoConverter dtoConverter;

    @GetMapping("api/genres")
    public List<GenreDto> getAllAuthors() {
        return dtoConverter.genresToDto(genreService.readAll());
    }
}
