package ru.otus.spring.hw11.rest;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;
import  ru.otus.spring.hw11.service.BookHandler;

import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Component
@RequiredArgsConstructor
public class BookRoutesDefinition {

    private final BookHandler bookHandler;

    @Bean
    public RouterFunction<ServerResponse> composedBookRoutes() {
        return route()
                .GET("/api/books", bookHandler::listAll)
                .GET("/api/books/{id}", bookHandler::getOne)
                .DELETE("/api/books/{id}", bookHandler::deleteOne)
                .POST("/api/books", bookHandler::addOne)
                .PUT("/api/books/{id}", bookHandler::editOne)
                .build();
    }

}
