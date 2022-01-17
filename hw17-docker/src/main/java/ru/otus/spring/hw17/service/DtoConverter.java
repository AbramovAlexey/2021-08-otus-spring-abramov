package ru.otus.spring.hw17.service;

import ru.otus.spring.hw17.dto.AuthorDto;
import ru.otus.spring.hw17.dto.BookDto;
import ru.otus.spring.hw17.dto.GenreDto;
import ru.otus.spring.hw17.model.Author;
import ru.otus.spring.hw17.model.Book;
import ru.otus.spring.hw17.model.Genre;

import java.util.List;

public interface DtoConverter {

    BookDto bookToDto(Book book);
    Book DtoToBook(BookDto bookDto);
    List<BookDto> booksToDto(List<Book> books);
    AuthorDto authorToDto(Author author);
    List<AuthorDto> authorsToDto(List<Author> authors);
    GenreDto genreToDto(Genre genre);
    List<GenreDto> genresToDto(List<Genre> genres);

}
