package ru.otus.spring.hw6;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.Import;
import ru.otus.spring.hw6.model.Author;
import ru.otus.spring.hw6.model.Book;
import ru.otus.spring.hw6.model.BookComment;
import ru.otus.spring.hw6.model.Genre;
import ru.otus.spring.hw6.repository.BookRepository;
import ru.otus.spring.hw6.repository.BookRepositoryJPA;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("Репозиторий Book должен")
@DataJpaTest
@Import(BookRepositoryJPA.class)
public class BookRepositoryJPATest {

    private static final int EXPECTED_BOOK_COUNT = 1;
    private static final int EXISTING_BOOK_ID = 1;
    private static final int EXISTING_AUTHOR_ID = 1;
    private static final int EXISTING_GENRE_ID = 1;
    private static final String EXISTING_BOOK_NAME = "War and Peace";

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
        var bookComment = new BookComment(0, "Good book", expectedBook);
        expectedBook.setBookComments(List.of(bookComment));
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
        var existingBook = bookRepository.findById(EXISTING_BOOK_ID);
        assertThat(existingBook).isPresent().get().isNotNull();
        em.detach(existingBook.get());
        bookRepository.deleteById(EXISTING_BOOK_ID);
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


}
