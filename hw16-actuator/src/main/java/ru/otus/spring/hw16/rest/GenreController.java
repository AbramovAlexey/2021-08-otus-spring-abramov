package ru.otus.spring.hw16.rest;

import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.otus.spring.hw16.dto.GenreDto;
import ru.otus.spring.hw16.security.config.PermissionExpression;
import ru.otus.spring.hw16.service.DtoConverter;
import ru.otus.spring.hw16.service.GenreService;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class GenreController {

    private final GenreService genreService;
    private final DtoConverter dtoConverter;

    @GetMapping("api/genres")
    @PreAuthorize(PermissionExpression.USER_OR_MANAGER)
    public List<GenreDto> getAllAuthors() {
        return dtoConverter.genresToDto(genreService.readAll());
    }
}
