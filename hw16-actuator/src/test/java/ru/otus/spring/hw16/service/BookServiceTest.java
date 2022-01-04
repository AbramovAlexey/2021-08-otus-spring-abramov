package ru.otus.spring.hw16.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import ru.otus.spring.hw16.model.Author;
import ru.otus.spring.hw16.model.Book;
import ru.otus.spring.hw16.model.Genre;
import ru.otus.spring.hw16.repository.BookRepository;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.when;

@DataMongoTest
@DisplayName("Service BookService must")
@Import({BookServiceImpl.class, GenreServiceImpl.class, AuthorServiceImpl.class, DtoConverterImpl.class})
public class BookServiceTest {

    @Autowired
    private BookService bookService;



    @MockBean
    private BookRepository bookRepository;

    private final Author author = new Author("id1", "authorName");
    private final Genre genre = new Genre("id1", "genreName");
    private final Book book = new Book("id1", "bookName", List.of(author), List.of(genre));

    @Test
    @DisplayName("Return correct book by name")
    void shouldReturnCorrectBookByName() {
        when(bookRepository.findByName(book.getName())).thenReturn(book);
        assertThat(bookService.readByName(book.getName())).isEqualTo(book);
    }

    @Test
    @DisplayName("Raise exception when not found")
    void shouldRaiseExceptionWhenNotFound() {
        when(bookRepository.findByName(book.getName())).thenReturn(null);
        assertThatThrownBy(() -> bookService.readByName(book.getName())).isInstanceOf(RuntimeException.class);
    }

    @Test
    @DisplayName("Return correct list of books")
    void shouldReturnCorrectListOfBooks() {
        when(bookRepository.findAll()).thenReturn(List.of(book));
        assertThat(bookService.readAll()).asList().containsExactly(book);
    }

    @Test
    @DisplayName("Raise exception when try to delete wrong author")
    void shouldRaiseExceptionWhenDeleteWrongAuthor() {
        when(bookRepository.findByName(book.getName())).thenReturn(book);
        assertThatThrownBy(() -> bookService.updateDeleteAuthor(book.getName(), "wrongAuthor")).isInstanceOf(RuntimeException.class);
    }

}
