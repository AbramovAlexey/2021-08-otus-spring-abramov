package ru.otus.spring.hw7;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import ru.otus.spring.hw7.model.Author;
import ru.otus.spring.hw7.model.Book;
import ru.otus.spring.hw7.model.Comment;
import ru.otus.spring.hw7.model.Genre;
import ru.otus.spring.hw7.repository.BookRepository;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("Репозиторий Book должен")
@DataJpaTest
public class BookRepositoryJPATest {

    private static final int EXPECTED_BOOK_COUNT = 1;
    private static final long EXISTING_BOOK_ID = 1;
    private static final long EXISTING_AUTHOR_ID = 1;
    private static final long EXISTING_GENRE_ID = 1;
    private static final String EXISTING_BOOK_NAME = "War and Peace";
    private static final String EXISTING_GENRE_NAME = "Fantasy";
    private static final String EXISTING_AUTHOR_NAME = "Tolstoy";

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private TestEntityManager em;

    @DisplayName("возвращать ожидаемое количество книг в БД")
    @Test
    public void shouldReturnCorrectCountBooks() {
        assertThat(bookRepository.count()).isEqualTo(EXPECTED_BOOK_COUNT);
    }

    @DisplayName("добавлять книгу в БД")
    @Test
    void shouldInsertBook(){
        var author =new Author(0, "Cargill");
        var genre = new Genre(0, "Poem");
        em.persistAndFlush(author);
        em.persistAndFlush(genre);
        Book expectedBook = new Book(0, "LOTR", List.of(author), List.of(genre), null);
        var bookComment = new Comment(0, "Good book", expectedBook);
        em.persist(bookComment);
        expectedBook.setComments(List.of(bookComment));
        bookRepository.save(expectedBook);
        em.flush();
        assertThat(expectedBook.getId()).isNotNull();
        em.detach(expectedBook);
        Book actualBook = bookRepository.findById(expectedBook.getId()).orElse(null);
        assertThat(actualBook).usingRecursiveComparison().isEqualTo(expectedBook) ;
    }

    @DisplayName("удалять заданную книгу по её id")
    @Test
    void shouldCorrectDeleteBookById() {
        var existingBook = bookRepository.findById(EXISTING_BOOK_ID).orElse(null);
        assertThat(existingBook).isNotNull();
        bookRepository.deleteById(EXISTING_BOOK_ID);
        em.flush();
        assertThat(bookRepository.findById(EXISTING_BOOK_ID).orElse(null)).isNull();
    }

    @DisplayName("возвращать ожидаемую кингу по её id")
    @Test
    void shouldReturnExpectedBookById() {
        var actualBook = bookRepository.findById(EXISTING_BOOK_ID);
        assertThat(actualBook).isPresent().get()
                              .matches(b -> b.getId() == EXISTING_BOOK_ID)
                              .matches(b -> b.getName().equals(EXISTING_BOOK_NAME))
                              .matches(b -> b.getAuthors().get(0).getId() == EXISTING_AUTHOR_ID)
                              .matches(b -> b.getGenres().get(0).getId() == EXISTING_GENRE_ID);
    }

    @DisplayName("возвращать ожидаемый список книг")
    @Test
    void shouldReturnExpectedBooksList() {
        Book expectedBook = bookRepository.findById(EXISTING_BOOK_ID).orElse(null);
        List<Book> actualBookList = bookRepository.findAll();
        assertThat(actualBookList)
                .usingFieldByFieldElementComparator()
                .containsExactlyInAnyOrder(expectedBook);
    }

    @DisplayName("обновлять книгу в БД")
    @Test
    void shouldUpdateBook() {
        var existingBook = bookRepository.findById(EXISTING_BOOK_ID).orElse(null);
        assertThat(existingBook).isNotNull();
        String newName = "Peace and War";
        existingBook.setName(newName);
        bookRepository.save(existingBook);
        em.flush();
        em.detach(existingBook);
        var updateBook = bookRepository.findById(EXISTING_BOOK_ID).orElse(null);
        assertThat(updateBook.getName()).isEqualTo(newName);
    }

    @DisplayName("возвращать книги с указанным названием жанра")
    @Test
    void shouldReturnBooksWithGenreName(){
        List<Book> books = bookRepository.findByGenresName(EXISTING_GENRE_NAME);
        assertThat(books).hasSize(1);
        assertThat(books.get(0)).hasFieldOrPropertyWithValue("id", EXISTING_BOOK_ID);
        books = bookRepository.findByGenresName("Name not exists");
        assertThat(books).hasSize(0);
    }

    @DisplayName("возвращать книги с указанным автором")
    @Test
    void shouldReturnBooksWithAuthorName(){
        List<Book> books = bookRepository.findByAuthorsFullName(EXISTING_AUTHOR_NAME);
        assertThat(books).hasSize(1);
        assertThat(books.get(0)).hasFieldOrPropertyWithValue("id", EXISTING_BOOK_ID);
        books = bookRepository.findByAuthorsFullName("Name not exists");
        assertThat(books).hasSize(0);
    }

}
