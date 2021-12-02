package ru.otus.spring.hw11.repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;
import ru.otus.spring.hw11.model.Author;

@Component
public interface AuthorRepository extends ReactiveMongoRepository<Author, String> {

    Mono<Author> findByName(String name);

}
