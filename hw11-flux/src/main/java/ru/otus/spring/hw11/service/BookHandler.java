package ru.otus.spring.hw11.service;

import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

public interface BookHandler {

    Mono<ServerResponse> listAll(ServerRequest request);
    Mono<ServerResponse> getOne(ServerRequest request);
    Mono<ServerResponse> deleteOne(ServerRequest request);
    Mono<ServerResponse> addOne(ServerRequest request);
    Mono<ServerResponse> editOne(ServerRequest request);

}
