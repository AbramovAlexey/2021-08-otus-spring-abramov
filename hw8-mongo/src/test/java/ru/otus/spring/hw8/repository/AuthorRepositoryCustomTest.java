package ru.otus.spring.hw8.repository;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import ru.otus.spring.hw8.model.Author;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataMongoTest
@DisplayName("Репозиторий AuthorRepositoryCustom должен")
public class AuthorRepositoryCustomTest {

    @Autowired
    private AuthorRepository authorRepository;

    @Test
    @DisplayName("извлекать список авторов из коллекции книг")
    void shouldExtractAuthorsFromBook() {
        List<Author> authorsFromBooks = authorRepository.findAuthorsFromBooks();
        List<Author> originalAuthors = authorRepository.findAll();
        assertThat(authorsFromBooks).hasSameElementsAs(originalAuthors);
    }

}
