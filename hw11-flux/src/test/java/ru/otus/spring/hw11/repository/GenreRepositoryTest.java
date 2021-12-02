package ru.otus.spring.hw11.repository;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;
import ru.otus.spring.hw11.model.Genre;

@DataMongoTest
public class GenreRepositoryTest {

    @Autowired
    private GenreRepository repository;

    @Test
    public void shouldFindGenreByName() {
        String name = "Fantasy";
        StepVerifier.create(repository.save(new Genre(name)))
                    .expectNextCount(1L)
                    .expectComplete()
                    .verify();
        Mono<Genre> genre = repository.findByName(name);
        StepVerifier
                .create(genre)
                .assertNext(g -> Assertions.assertNotNull(g.getId()))
                .expectComplete()
                .verify();
        genre = repository.findByName(name.repeat(2));
        StepVerifier
                .create(genre)
                .expectComplete()
                .verify();
    }


}
