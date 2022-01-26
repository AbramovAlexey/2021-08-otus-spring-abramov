package ru.otus.spring.hw18.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.spring.hw18.actuator.AddBookTracking;
import ru.otus.spring.hw18.actuator.DeleteBookTracking;
import ru.otus.spring.hw18.model.Author;
import ru.otus.spring.hw18.model.Book;
import ru.otus.spring.hw18.model.Comment;
import ru.otus.spring.hw18.model.Genre;
import ru.otus.spring.hw18.repository.BookRepository;
import ru.otus.spring.hw18.repository.CommentRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService{

    private final BookRepository bookRepository;
    private final GenreService genreService;
    private final AuthorService authorService;
    private final CommentRepository commentRepository;

    @Override
    public Book readByName(String name) {
        return Optional.ofNullable(bookRepository.findByName(name))
                       .orElseThrow(() -> new RuntimeException(String.format("Book with name '%s' not found", name)));
    }

    @Override
    public List<Book> readAll() {
        return bookRepository.findAll();
    }

    @Override
    public void updateDeleteAuthor(String nameBook, String nameAuthor) {
        Book book = readByName(nameBook);
        List<Author> authors = book.getAuthors();
        var matchAuthor = findAuthorByName(authors, nameAuthor);
        authors.remove(matchAuthor.orElseThrow(() -> new RuntimeException("No such author for this book")));
        bookRepository.save(book);
    }

    public Book readById(String id) {
        return bookRepository.findById(id).orElse(null);
    }

    @Override
    @DeleteBookTracking
    public void deleteById(String id) {
        bookRepository.deleteById(id);
    }

    @Override
    @AddBookTracking
    public Book save(Book book) {
        return bookRepository.save(book);
    }

    private Optional<Genre> findGenreByName(List<Genre> genres, String name) {
        return genres.stream()
                     .filter(genre -> genre.getName().equals(name))
                     .findFirst();
    }

    private Optional<Author> findAuthorByName(List<Author> authors, String name) {
        return authors.stream()
                      .filter(author -> author.getName().equals(name))
                      .findFirst();
    }

}
