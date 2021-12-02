package ru.otus.spring.hw11.rest;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;
import ru.otus.spring.hw11.dto.AuthorDto;
import ru.otus.spring.hw11.repository.AuthorRepository;

import static org.springframework.web.reactive.function.server.RouterFunctions.route;
import static org.springframework.web.reactive.function.server.ServerResponse.ok;

@Component
@RequiredArgsConstructor
public class AuthorRoutesDefinition {

    private final AuthorRepository authorRepository;

    @Bean
    public RouterFunction<ServerResponse> composedAuthorRoutes() {
        return route()
                .GET("/api/authors",
                    request -> ok().contentType(MediaType.APPLICATION_JSON)
                                   .body(authorRepository.findAll()
                                                         .map(author -> new AuthorDto(author.getId(), author.getName())),
                                         AuthorDto.class)
                ).build();

    }

}
