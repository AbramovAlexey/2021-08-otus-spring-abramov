package ru.otus.spring.hw10.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.spring.hw10.dto.AuthorDto;
import ru.otus.spring.hw10.dto.BookDto;
import ru.otus.spring.hw10.dto.GenreDto;
import ru.otus.spring.hw10.model.Author;
import ru.otus.spring.hw10.model.Book;
import ru.otus.spring.hw10.model.Genre;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DtoConverterImpl implements DtoConverter{

    private static final String DELIMITER = ",";
    private final GenreService genreService;
    private final AuthorService authorService;

    @Override
    public BookDto bookToDto(Book book) {
        return new BookDto(book.getId(),
                           book.getName(),
                           book.getAuthors().stream().map(Author::getName).collect(Collectors.joining(DELIMITER)),
                           book.getGenres().stream().map(Genre::getName).collect(Collectors.joining(DELIMITER)));
    }

    @Override
    public Book DtoToBook(BookDto bookDto) {
        return new Book(bookDto.getId(),
                        bookDto.getName(),
                        Arrays.stream(bookDto.getAuthors().split(DELIMITER))
                              .map(authorService::readOrCreate)
                              .collect(Collectors.toList()),
                        Arrays.stream(bookDto.getGenres().split(DELIMITER))
                              .map(genreService::readOrCreate)
                              .collect(Collectors.toList())
                );
    }

    @Override
    public List<BookDto> booksToDto(List<Book> books) {
        return books.stream().map(this::bookToDto).collect(Collectors.toList());
    }



    @Override
    public AuthorDto authorToDto(Author author) {
        return new AuthorDto(author.getId(), author.getName());
    }

    @Override
    public List<AuthorDto> authorsToDto(List<Author> authors) {
        return authors.stream().map(this::authorToDto).collect(Collectors.toList());
    }

    @Override
    public GenreDto genreToDto(Genre genre) {
        return new GenreDto(genre.getId(), genre.getName());
    }

    @Override
    public List<GenreDto> genresToDto(List<Genre> genres) {
        return genres.stream().map(this::genreToDto).collect(Collectors.toList());
    }

}
