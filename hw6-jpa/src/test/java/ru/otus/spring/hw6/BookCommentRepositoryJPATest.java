package ru.otus.spring.hw6;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.Import;
import ru.otus.spring.hw6.model.Book;
import ru.otus.spring.hw6.model.BookComment;
import ru.otus.spring.hw6.repository.BookCommentRepository;
import ru.otus.spring.hw6.repository.BookCommentRepositoryJPA;
import ru.otus.spring.hw6.repository.BookRepository;
import ru.otus.spring.hw6.repository.BookRepositoryJPA;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("Репозиторий BookComment должен")
@DataJpaTest
@Import({BookCommentRepositoryJPA.class, BookRepositoryJPA.class})
public class BookCommentRepositoryJPATest {

    private static final int EXPECTED_BOOK_COMMENT_COUNT = 0;
    private static final int EXISTING_BOOK_ID = 1;

    @Autowired
    private BookCommentRepository bookCommentRepository;

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private TestEntityManager em;

    @DisplayName("возвращать ожидаемое количество комментариев в БД")
    @Test
    public void shouldReturnCorrectCountBooks() {
        assertThat(bookCommentRepository.count()).isEqualTo(EXPECTED_BOOK_COMMENT_COUNT);
    }

    @DisplayName("добавлять книгу в БД")
    @Test
    void shouldInsertBook(){
        Book existingBook = bookRepository.findById(EXISTING_BOOK_ID).orElse(null);
        BookComment bookComment = new BookComment(0, "Good book", existingBook);
        bookCommentRepository.save(bookComment);
        assertThat(bookComment.getId()).isGreaterThan(0);
        em.detach(bookComment);
        var actualBookComment =  bookCommentRepository.findById(bookComment.getId());
        assertThat(actualBookComment).isPresent().get()
                                     .usingRecursiveComparison()
                                     .isEqualTo(bookComment);
    }

    /*
    @DisplayName("удалять заданную книгу по её id")
    @Test
    void shouldCorrectDeleteBookById() {
        var existingBook = bookCommentRepository.findById(EXISTING_BOOK_ID);
        assertThat(existingBook).isPresent().get().isNotNull();
        em.detach(existingBook.get());
        bookCommentRepository.deleteById(EXISTING_BOOK_ID);
        assertThat(bookCommentRepository.findById(EXISTING_BOOK_ID).orElse(null)).isNull();
    }

    @DisplayName("возвращать ожидаемую кингу по её id")
    @Test
    void shouldReturnExpectedBookById() {
        var actualBook = bookCommentRepository.findById(EXISTING_BOOK_ID);
        assertThat(actualBook).isPresent().get()
                              .matches(b -> b.getId() == EXISTING_BOOK_ID)
                              .matches(b -> b.getName().equals(EXISTING_BOOK_NAME))
                              .matches(b -> b.getAuthors().get(0).getId() == EXISTING_AUTHOR_ID)
                              .matches(b -> b.getGenres().get(0).getId() == EXISTING_GENRE_ID);
    }

    @DisplayName("возвращать ожидаемый список книг")
    @Test
    void shouldReturnExpectedBooksList() {
        BookComment expectedBook = bookCommentRepository.findById(EXISTING_BOOK_ID).orElse(null);
        List<BookComment> actualBookList = bookCommentRepository.findAll();
        assertThat(actualBookList)
                .usingFieldByFieldElementComparator()
                .containsExactlyInAnyOrder(expectedBook);
    }

    @DisplayName("обновлять книгу в БД")
    @Test
    void shouldUpdateBook() {
        var existingBook = bookCommentRepository.findById(EXISTING_BOOK_ID).orElse(null);
        assertThat(existingBook).isNotNull();
        String newName = "Peace and War";
        existingBook.setName(newName);
        bookCommentRepository.save(existingBook);
        em.flush();
        em.detach(existingBook);
        var updateBook = bookCommentRepository.findById(EXISTING_BOOK_ID).orElse(null);
        assertThat(updateBook.getName()).isEqualTo(newName);
    }
*/

}
