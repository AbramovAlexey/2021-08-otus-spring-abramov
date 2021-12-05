package ru.otus.spring.hw11.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.otus.spring.hw11.dto.BookDto;
import ru.otus.spring.hw11.model.Author;
import ru.otus.spring.hw11.model.Book;
import ru.otus.spring.hw11.model.Genre;
import ru.otus.spring.hw11.repository.AuthorRepository;
import ru.otus.spring.hw11.repository.BookRepository;
import ru.otus.spring.hw11.repository.GenreRepository;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.web.reactive.function.server.ServerResponse.notFound;
import static org.springframework.web.reactive.function.server.ServerResponse.ok;

@Service
@RequiredArgsConstructor
public class BookHandlerImpl implements BookHandler{

    public static final String DELIMITER = ",";

    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;
    private final GenreRepository genreRepository;

    @Override
    public Mono<ServerResponse> listAll(ServerRequest request) {
        return ok().contentType(APPLICATION_JSON)
                   .body(bookRepository.findAll().flatMap(this::toDto), BookDto.class);
    }

    @Override
    public Mono<ServerResponse> getOne(ServerRequest request) {
        return bookRepository.findById(request.pathVariable("id"))
                             .flatMap(book -> ok().contentType(APPLICATION_JSON).body(toDto(book), BookDto.class))
                             .switchIfEmpty(notFound().build());
    }

    @Override
    public Mono<ServerResponse> deleteOne(ServerRequest request) {
        return bookRepository.deleteById(request.pathVariable("id"))
                             .flatMap(v -> ok().build());
    }

    @Override
    public Mono<ServerResponse> addOne(ServerRequest request) {
        return request.bodyToMono(BookDto.class)
                .flatMap(this::toBook)
                .flatMap(bookRepository::save)
                .flatMap(book -> ok().contentType(APPLICATION_JSON).build());
    }

    @Override
    public Mono<ServerResponse> editOne(ServerRequest request) {
        return bookRepository.findById(request.pathVariable("id"))
                      .flatMap(book -> request.bodyToMono(BookDto.class)
                                              .flatMap(bookDto -> {
                                                  bookDto.setId(book.getId());
                                                  return toBook(bookDto);
                      }))
                      .flatMap(bookRepository::save)
                      .flatMap(book -> ok().contentType(APPLICATION_JSON).build())
                      .switchIfEmpty(notFound().build());
    }

    private Mono<BookDto> toDto(Book book) {
        return Mono.just(book)
                   .zipWith(authorRepository.findAllById(book.getAuthorIds()).collectList(),
                            (b, authors) -> {
                                BookDto bookDto = new BookDto();
                                bookDto.setId(b.getId());
                                bookDto.setName(b.getName());
                                if (Objects.nonNull(authors)) {
                                    bookDto.setAuthors(authors.stream().map(Author::getName).collect(Collectors.joining(DELIMITER)));
                                }
                                return bookDto;
                   })
                   .zipWith(genreRepository.findAllById(book.getGenreIds()).collectList(),
                            (dto, genres) -> {
                                if (Objects.nonNull(genres)) {
                                    dto.setGenres(genres.stream().map(Genre::getName).collect(Collectors.joining(DELIMITER)));
                                }
                                return dto;
                   });
    }

    private Mono<Book> toBook(BookDto bookDto) {
        return Mono.just(bookDto)
                   .zipWith(readOrCreateAuthors(bookDto.getAuthors()),
                               (dto, authors) -> {
                                    Book book = new Book();
                                    book.setId(bookDto.getId());
                                    book.setName(bookDto.getName());
                                    book.setAuthorIds(authors.stream().map(Author::getId).collect(Collectors.toList()));
                                    return book;
                   })
                   .zipWith(readOrCreateGenres(bookDto.getGenres()),
                               (book, genres) -> {
                                    book.setGenreIds(genres.stream().map(Genre::getId).collect(Collectors.toList()));
                                    return book;
                   });
    }

    private Mono<List<Author>> readOrCreateAuthors(String names) {
        return Flux.fromIterable(Arrays.stream(names.split(DELIMITER))
                   .collect(Collectors.toMap(s -> s, s -> authorRepository.findByName(s)
                                      .switchIfEmpty(authorRepository.save(new Author(s)))))
                   .values()
        ).flatMap(a -> a).collectList();
    }

    private Mono<List<Genre>> readOrCreateGenres(String names) {
        return Flux.fromIterable(Arrays.stream(names.split(DELIMITER))
                   .collect(Collectors.toMap(s -> s, s -> genreRepository.findByName(s)
                                      .switchIfEmpty(genreRepository.save(new Genre(s)))))
                   .values()
        ).flatMap(g -> g).collectList();
    }

}
