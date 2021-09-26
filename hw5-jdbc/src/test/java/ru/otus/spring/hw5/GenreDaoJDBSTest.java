package ru.otus.spring.hw5;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.context.annotation.Import;
import org.springframework.dao.EmptyResultDataAccessException;
import ru.otus.spring.hw5.dao.GenreDao;
import ru.otus.spring.hw5.dao.GenreDaoJDBC;
import ru.otus.spring.hw5.domain.Genre;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

@DisplayName("Dao для работы с жанрами должно")
@JdbcTest
@Import(GenreDaoJDBC.class)
public class GenreDaoJDBSTest {

    private static final int EXPECTED_GENRE_COUNT = 1;
    private static final int EXISTING_GENRE_ID = 1;
    private static final String EXISTING_GENRE_NAME = "Fantasy";

    @Autowired
    private GenreDao genreDao;

    @DisplayName("возвращать ожидаемое количество жанров в БД")
    @Test
    void shouldReturnExpectedGenreCount() {
        int actualGenresCount = genreDao.count();
        assertThat(actualGenresCount).isEqualTo(EXPECTED_GENRE_COUNT);
    }

    @DisplayName("добавлять жанр в БД")
    @Test
    void shouldInsertGenre(){
        Genre expectedGenre = new Genre(-1,"Mystery");
        long actualId = genreDao.insert(expectedGenre);
        Genre actualGenre = genreDao.getById(actualId);
        assertThat(actualGenre.getName()).isEqualTo(expectedGenre.getName());
    }

    @DisplayName("возвращать ожидаемый жанр по его id")
    @Test
    void shouldReturnExpectedGenreById() {
        Genre expectedGenre = new Genre(EXISTING_GENRE_ID, EXISTING_GENRE_NAME);
        Genre actualGenre = genreDao.getById(expectedGenre.getId());
        assertThat(actualGenre).usingRecursiveComparison().isEqualTo(expectedGenre);
    }

    @DisplayName("удалять заданный жанр по его id")
    @Test
    void shouldCorrectDeleteGenreById() {
        assertThatCode(() -> genreDao.getById(EXISTING_GENRE_ID))
                .doesNotThrowAnyException();
        genreDao.deleteById(EXISTING_GENRE_ID);
        assertThatThrownBy(() -> genreDao.getById(EXISTING_GENRE_ID))
                .isInstanceOf(EmptyResultDataAccessException.class);
    }

    @DisplayName("возвращать ожидаемый список жанров")
    @Test
    void shouldReturnExpectedGenresList() {
        Genre expectedGenre = new Genre(EXISTING_GENRE_ID, EXISTING_GENRE_NAME);
        List<Genre> actualGenreList = genreDao.getAll();
        assertThat(actualGenreList)
                .usingFieldByFieldElementComparator()
                .containsExactlyInAnyOrder(expectedGenre);
    }

    @DisplayName("обновлять жанр в БД")
    @Test
    void shouldUpdateGenre() {
        String newName = "Cook-books";
        Genre genre = genreDao.getById(EXISTING_GENRE_ID);
        assertThat(genre).isNotNull();
        assertThat(genre.getName()).isEqualTo(EXISTING_GENRE_NAME);
        Genre genreForUpdate = new Genre(genre.getId(), newName);
        genreDao.update(genreForUpdate);
        Genre updatedGenre = genreDao.getById(EXISTING_GENRE_ID);
        assertThat(updatedGenre.getName()).isEqualTo(newName);
    }

}
