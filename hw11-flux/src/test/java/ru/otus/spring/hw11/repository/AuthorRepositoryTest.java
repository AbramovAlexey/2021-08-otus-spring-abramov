package ru.otus.spring.hw11.repository;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;
import ru.otus.spring.hw11.model.Author;

@DataMongoTest
public class AuthorRepositoryTest {

    @Autowired
    private AuthorRepository repository;

    @Test
    public void shouldFindAuthorByName() {
        String name = "Tolkien";
        StepVerifier.create(repository.save(new Author(name)))
                    .expectNextCount(1L)
                    .expectComplete()
                    .verify();
        Mono<Author> author = repository.findByName(name);
        StepVerifier
                .create(author)
                .assertNext(a -> Assertions.assertNotNull(a.getId()))
                .expectComplete()
                .verify();
        author = repository.findByName(name.repeat(2));
        StepVerifier
                .create(author)
                .expectComplete()
                .verify();
    }


}
