package ru.otus.spring.hw6;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import ru.otus.spring.hw6.repository.AuthorRepository;
import ru.otus.spring.hw6.repository.AuthorRepositoryJPA;

import static org.assertj.core.api.Assertions.*;

@DisplayName("Репозиторий Author должен")
@DataJpaTest
@Import(AuthorRepositoryJPA.class)
public class AuthorRepositoryJPATest {

    private static final int AUTHORS_COUNT = 2;

    @Autowired
    private AuthorRepository authorRepository;

    @DisplayName("возвращать правильное количество авторов")
    @Test
    public void shouldReturnCorrectCountAuthors() {
        assertThat(authorRepository.count()).isEqualTo(AUTHORS_COUNT);
    }

}
