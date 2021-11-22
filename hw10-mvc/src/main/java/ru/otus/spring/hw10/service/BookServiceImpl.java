package ru.otus.spring.hw10.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.spring.hw10.model.Author;
import ru.otus.spring.hw10.model.Book;
import ru.otus.spring.hw10.model.Comment;
import ru.otus.spring.hw10.model.Genre;
import ru.otus.spring.hw10.repository.BookRepository;
import ru.otus.spring.hw10.repository.CommentRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService{

    private final BookRepository bookRepository;
    private final GenreService genreService;
    private final AuthorService authorService;
    private final CommentRepository commentRepository;
    private final DtoConverter dtoConverter;

    @Override
    public Book create(String name, String authorName, String genreName) {
        Author author = authorService.readByName(authorName);
        Genre genre = genreService.readByName(genreName);
        return bookRepository.save(new Book(name, List.of(author), List.of(genre)));
    }

    @Override
    public void update(String oldName, String newName) {
        Book book = bookRepository.findByName(oldName);
        book.setName(newName);
        bookRepository.save(book);
    }

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
    public void deleteByName(String name) {
        bookRepository.deleteByName(name);
    }

    @Override
    public void updateDeleteGenre(String nameBook, String nameGenre) {
        Book book = readByName(nameBook);
        List<Genre> genres = book.getGenres();
        var matchGenre = findGenreByName(genres, nameGenre);
        genres.remove(matchGenre.orElseThrow(() -> new RuntimeException("No such genre for this book")));
        bookRepository.save(book);
    }

    @Override
    public void updateAddGenre(String nameBook, String nameGenre) {
        Book book = readByName(nameBook);
        List<Genre> genres = book.getGenres();
        if (findGenreByName(genres, nameGenre).isPresent()) {
            throw  new RuntimeException("The book already have such genre");
        } else {
            genres.add(genreService.readByName(nameGenre));
            bookRepository.save(book);
        }
    }

    @Override
    public void updateDeleteAuthor(String nameBook, String nameAuthor) {
        Book book = readByName(nameBook);
        List<Author> authors = book.getAuthors();
        var matchAuthor = findAuthorByName(authors, nameAuthor);
        authors.remove(matchAuthor.orElseThrow(() -> new RuntimeException("No such author for this book")));
        bookRepository.save(book);
    }

    @Override
    public void updateAddAuthor(String nameBook, String nameAuthor) {
        Book book = readByName(nameBook);
        List<Author> authors = book.getAuthors();
        if (findAuthorByName(authors, nameAuthor).isPresent()) {
            throw  new RuntimeException("The book already have such author");
        } else {
            authors.add(authorService.readByName(nameAuthor));
            bookRepository.save(book);
        }
    }

    @Override
    public String addComment(String nameBook, String text) {
        Book book = readByName(nameBook);
        return commentRepository.save(new Comment(book.getId(), text))
                                .getId();
    }

    @Override
    public void deleteComment(String idComment) {
        commentRepository.deleteById(idComment);
    }

    @Override
    public List<Comment> showAllComments(String nameBook) {
        Book book = readByName(nameBook);
        return commentRepository.findAllByBookId(book.getId());
    }

    @Override
    public List<Book> findByAuthor(String name) {
        return bookRepository.findAllByAuthorsName(name);
    }

    @Override
    public List<Book> findByGenre(String name) {
        return bookRepository.findAllByGenresName(name);
    }

    public Book readById(String id) {
        return bookRepository.findById(id).orElse(null);
    }

    @Override
    public void deleteById(String id) {
        bookRepository.deleteById(id);
    }

    @Override
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
