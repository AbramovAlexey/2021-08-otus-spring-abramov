package ru.otus.spring.hw11.rest;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;
import ru.otus.spring.hw11.dto.GenreDto;
import ru.otus.spring.hw11.repository.GenreRepository;

import static org.springframework.web.reactive.function.server.RouterFunctions.route;
import static org.springframework.web.reactive.function.server.ServerResponse.ok;

@Component
@RequiredArgsConstructor
public class GenreRoutesDefinition {

    private final GenreRepository genreRepository;

    @Bean
    public RouterFunction<ServerResponse> composedGenreRoutes() {
        return route()
                .GET("/api/genres",
                        request -> ok().contentType(MediaType.APPLICATION_JSON)
                                       .body(genreRepository.findAll()
                                                            .map(genre -> new GenreDto(genre.getId(), genre.getName())),
                                             GenreDto.class)
                ).build();
    }

}
