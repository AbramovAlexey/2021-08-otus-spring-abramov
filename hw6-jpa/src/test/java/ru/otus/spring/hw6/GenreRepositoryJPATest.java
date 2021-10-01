package ru.otus.spring.hw6;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.Import;
import ru.otus.spring.hw6.model.Genre;
import ru.otus.spring.hw6.repository.GenreRepository;
import ru.otus.spring.hw6.repository.GenreRepositoryJPA;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("Репозиторий Genre должен")
@DataJpaTest
@Import(GenreRepositoryJPA.class)
public class GenreRepositoryJPATest {

    private static final int EXPECTED_GENRE_COUNT = 1;
    private static final long EXISTING_GENRE_ID = 1;
    private static final String EXISTING_GENRE_NAME = "Fantasy";

    @Autowired
    private GenreRepository genreRepository;

    @Autowired
    private TestEntityManager em;

    @DisplayName("возвращать ожидаемое количество жанров в БД")
    @Test
    public void shouldReturnCorrectCountGenres() {
        assertThat(genreRepository.count()).isEqualTo(EXPECTED_GENRE_COUNT);
    }

    @DisplayName("добавлять жанр в БД")
    @Test
    void shouldInsertGenre(){
        Genre expectedGenre = new Genre(0, "Mystery");
        genreRepository.save(expectedGenre);
        assertThat(expectedGenre.getId()).isNotNull();
        Genre actualGenre = genreRepository.findById(expectedGenre.getId()).orElse(null);
        assertThat(actualGenre).usingRecursiveComparison().isEqualTo(expectedGenre) ;
    }

    @DisplayName("удалять заданный жанр по его id")
    @Test
    void shouldCorrectDeleteGenreById() {
        var existingGenre = genreRepository.findById(EXISTING_GENRE_ID);
        assertThat(existingGenre).isPresent().get().isNotNull();
        em.detach(existingGenre.get());
        genreRepository.deleteById(EXISTING_GENRE_ID);
        assertThat(genreRepository.findById(EXISTING_GENRE_ID).orElse(null)).isNull();
    }

    @DisplayName("возвращать ожидаемый жанр по его id")
    @Test
    void shouldReturnExpectedGenreById() {
        Genre expectedGenre = new Genre(EXISTING_GENRE_ID, EXISTING_GENRE_NAME);
        var actualGenre = genreRepository.findById(expectedGenre.getId());
        assertThat(actualGenre).isPresent().get()
                               .matches(g -> g.getId() == EXISTING_GENRE_ID)
                               .matches(g -> g.getName().equals(EXISTING_GENRE_NAME));
    }

    @DisplayName("возвращать ожидаемый список жанров")
    @Test
    void shouldReturnExpectedGenresList() {
        Genre expectedGenre = new Genre(EXISTING_GENRE_ID, EXISTING_GENRE_NAME);
        List<Genre> actualGenreList = genreRepository.findAll();
        assertThat(actualGenreList)
                .allMatch(g -> g.getId() == expectedGenre.getId())
                .allMatch(g -> g.getName().equals(expectedGenre.getName()));
    }

    @DisplayName("обновлять жанр в БД")
    @Test
    void shouldUpdateGenre() {
        String newName = "Cook-books";
        Genre Genre = genreRepository.findById(EXISTING_GENRE_ID).orElse(null);
        assertThat(Genre).isNotNull();
        assertThat(Genre.getName()).isEqualTo(EXISTING_GENRE_NAME);
        em.detach(Genre);
        Genre.setName(newName);
        genreRepository.save(Genre);
        Genre updatedGenre = genreRepository.findById(EXISTING_GENRE_ID).orElse(null);
        assertThat(updatedGenre.getName()).isEqualTo(newName);
    }


}
