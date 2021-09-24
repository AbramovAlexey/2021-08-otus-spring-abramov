package ru.otus.spring.hw5;

import org.assertj.core.api.recursive.comparison.RecursiveComparisonConfiguration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.context.annotation.Import;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import ru.otus.spring.hw5.dao.AuthorDaoJDBC;
import ru.otus.spring.hw5.dao.BookDao;
import ru.otus.spring.hw5.dao.BookDaoJDBC;
import ru.otus.spring.hw5.dao.GenreDaoJDBC;
import ru.otus.spring.hw5.domain.Book;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

@DisplayName("Dao для работы с книгами должно")
@JdbcTest
@Import({BookDaoJDBC.class})
public class BookDaoJDBSTest {

    private static final int EXPECTED_BOOK_COUNT = 1;
    private static final int EXISTING_BOOK_ID = 1;
    private static final int EXISTING_AUTHOR_ID = 1;
    private static final int NOT_EXISTING_AUTHOR_ID = 500;
    private static final int EXISTING_GENRE_ID = 1;
    private static final String EXISTING_BOOK_NAME = "War and Peace";
    private static RecursiveComparisonConfiguration recursiveComparisonConfiguration;

    @Autowired
    private BookDao bookDao;

    @BeforeAll
    public static void beforeAll() {
        recursiveComparisonConfiguration = new RecursiveComparisonConfiguration();
        recursiveComparisonConfiguration.ignoreFields("author.fullName", "genre.name");
    }

    @DisplayName("возвращать ожидаемое количество книг в БД")
    @Test
    void shouldReturnExpectedBookCount() {
        int actualBooksCount = bookDao.count();
        assertThat(actualBooksCount).isEqualTo(EXPECTED_BOOK_COUNT);
    }

    @DisplayName("добавлять книгу в БД")
    @Test
    void shouldInsertBook(){
        Book expectedBook = new Book("LOTR", EXISTING_AUTHOR_ID, EXISTING_GENRE_ID);
        long actualId = bookDao.insert(expectedBook);
        Book actualBook = bookDao.getById(actualId);
        assertThat(actualBook.getName()).isEqualTo(expectedBook.getName());
    }

    @DisplayName("генерировать ошибку при несуществующем авторе")
    @Test
    void shouldFailInsertAuthorNotExist(){
        Book expectedBook = new Book("LOTR", NOT_EXISTING_AUTHOR_ID, EXISTING_GENRE_ID);
        assertThatThrownBy(() -> bookDao.insert(expectedBook))
                .isInstanceOf(DataIntegrityViolationException.class);
    }

    @DisplayName("возвращать ожидаемую книгу по её id")
    @Test
    void shouldReturnExpectedBookById() {
        Book expectedBook = new Book(EXISTING_BOOK_ID, EXISTING_BOOK_NAME, EXISTING_AUTHOR_ID, EXISTING_GENRE_ID);
        Book actualBook = bookDao.getById(expectedBook.getId());
        assertThat(actualBook).usingRecursiveComparison(recursiveComparisonConfiguration)
                              .isEqualTo(expectedBook);
    }

    @DisplayName("удалять заданную книгу по её id")
    @Test
    void shouldCorrectDeleteBookById() {
        assertThatCode(() -> bookDao.getById(EXISTING_BOOK_ID))
                .doesNotThrowAnyException();
        bookDao.deleteById(EXISTING_BOOK_ID);
        assertThatThrownBy(() -> bookDao.getById(EXISTING_BOOK_ID))
                .isInstanceOf(EmptyResultDataAccessException.class);
    }

    @DisplayName("возвращать ожидаемый список книг")
    @Test
    void shouldReturnExpectedBooksList() {
        Book expectedBook = new Book(EXISTING_BOOK_ID, EXISTING_BOOK_NAME, EXISTING_AUTHOR_ID, EXISTING_GENRE_ID);
        List<Book> actualBookList = bookDao.getAll();
        assertThat(actualBookList)
                .usingRecursiveFieldByFieldElementComparator(recursiveComparisonConfiguration)
                .containsExactlyInAnyOrder(expectedBook);
    }

    @DisplayName("обновлять книгу в БД")
    @Test
    void shouldUpdateBook() {
        String newName = "Peace and War";
        Book book = bookDao.getById(EXISTING_BOOK_ID);
        assertThat(book).isNotNull();
        assertThat(book.getName()).isEqualTo(EXISTING_BOOK_NAME);
        assertThat(book.getAuthor().getId()).isEqualTo(EXISTING_AUTHOR_ID);
        assertThat(book.getGenre().getId()).isEqualTo(EXISTING_GENRE_ID);
        Book BookForUpdate = new Book(book.getId(), newName, EXISTING_AUTHOR_ID, EXISTING_GENRE_ID);
        bookDao.update(BookForUpdate);
        Book updatedBook = bookDao.getById(EXISTING_BOOK_ID);
        assertThat(updatedBook.getName()).isEqualTo(newName);
    }

}
