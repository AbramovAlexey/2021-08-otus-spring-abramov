package ru.otus.spring.hw5;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.context.annotation.Import;
import org.springframework.dao.EmptyResultDataAccessException;
import ru.otus.spring.hw5.dao.AuthorDao;
import ru.otus.spring.hw5.dao.AuthorDaoJDBC;
import ru.otus.spring.hw5.domain.Author;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

@DisplayName("Dao для работы с авторами должно")
@JdbcTest
@Import(AuthorDaoJDBC.class)
public class AuthorDaoJDBSTest {

    private static final int EXPECTED_AUTHOR_COUNT = 2;
    private static final int EXISTING_AUTHOR_ID = 1;
    private static final String EXISTING_AUTHOR_NAME = "Tolstoy";
    public static final int EXISTING_SECOND_AUTHOR_ID = 2;
    public static final String EXISTING_SECOND_AUTHOR_NAME = "Lermontov";

    @Autowired
    private AuthorDao authorDao;

    @DisplayName("возвращать ожидаемое количество авторов в БД")
    @Test
    void shouldReturnExpectedAuthorCount() {
        int actualAuthorsCount = authorDao.count();
        assertThat(actualAuthorsCount).isEqualTo(EXPECTED_AUTHOR_COUNT);
    }

    @DisplayName("добавлять автора в БД")
    @Test
    void shouldInsertAuthor(){
        Author expectedAuthor = new Author(-1, "Tolkien");
        long actualId = authorDao.insert(expectedAuthor);
        Author actualAuthor = authorDao.getById(actualId);
        assertThat(actualAuthor.getFullName()).isEqualTo(expectedAuthor.getFullName());
    }

    @DisplayName("возвращать ожидаемого автора по его id")
    @Test
    void shouldReturnExpectedAuthorById() {
        Author expectedAuthor = new Author(EXISTING_AUTHOR_ID, EXISTING_AUTHOR_NAME);
        Author actualAuthor = authorDao.getById(expectedAuthor.getId());
        assertThat(actualAuthor).usingRecursiveComparison().isEqualTo(expectedAuthor);
    }

    @DisplayName("удалять заданного автора по его id")
    @Test
    void shouldCorrectDeleteAuthorById() {
        assertThatCode(() -> authorDao.getById(EXISTING_AUTHOR_ID))
                .doesNotThrowAnyException();
        authorDao.deleteById(EXISTING_AUTHOR_ID);
        assertThatThrownBy(() -> authorDao.getById(EXISTING_AUTHOR_ID))
                .isInstanceOf(EmptyResultDataAccessException.class);
    }

    @DisplayName("возвращать ожидаемый список авторов")
    @Test
    void shouldReturnExpectedAuthorsList() {
        Author expectedAuthor = new Author(EXISTING_AUTHOR_ID, EXISTING_AUTHOR_NAME);
        Author secondExpectedAuthor = new Author(EXISTING_SECOND_AUTHOR_ID, EXISTING_SECOND_AUTHOR_NAME);
        List<Author> actualAuthorList = authorDao.getAll();
        assertThat(actualAuthorList)
                .usingFieldByFieldElementComparator()
                .containsExactlyInAnyOrder(expectedAuthor, secondExpectedAuthor);
    }

    @DisplayName("обновлять автора в БД")
    @Test
    void shouldUpdateAuthor() {
        String newFullName = "newFullName";
        Author author = authorDao.getById(EXISTING_AUTHOR_ID);
        assertThat(author).isNotNull();
        assertThat(author.getFullName()).isEqualTo(EXISTING_AUTHOR_NAME);
        Author authorForUpdate = new Author(author.getId(), newFullName);
        authorDao.update(authorForUpdate);
        Author updatedAuthor = authorDao.getById(EXISTING_AUTHOR_ID);
        assertThat(updatedAuthor.getFullName()).isEqualTo(newFullName);
    }

}
