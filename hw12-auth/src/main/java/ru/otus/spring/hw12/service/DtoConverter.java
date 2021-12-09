package ru.otus.spring.hw12.service;

import ru.otus.spring.hw12.dto.AuthorDto;
import ru.otus.spring.hw12.dto.BookDto;
import ru.otus.spring.hw12.dto.GenreDto;
import ru.otus.spring.hw12.model.Author;
import ru.otus.spring.hw12.model.Book;
import ru.otus.spring.hw12.model.Genre;

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
