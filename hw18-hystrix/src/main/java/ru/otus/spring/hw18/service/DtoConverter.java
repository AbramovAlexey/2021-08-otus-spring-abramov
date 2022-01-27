package ru.otus.spring.hw18.service;

import ru.otus.spring.hw18.dto.AuthorDto;
import ru.otus.spring.hw18.dto.BookDto;
import ru.otus.spring.hw18.dto.GenreDto;
import ru.otus.spring.hw18.model.Author;
import ru.otus.spring.hw18.model.Book;
import ru.otus.spring.hw18.model.Genre;

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
