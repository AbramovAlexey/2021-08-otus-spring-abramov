package ru.otus.spring.hw7.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.spring.hw7.model.Book;
import ru.otus.spring.hw7.model.Comment;
import ru.otus.spring.hw7.model.Genre;
import ru.otus.spring.hw7.repository.BookRepository;
import ru.otus.spring.hw7.repository.CommentRepository;

import java.util.List;

import static ru.otus.spring.hw7.utils.Utils.checkAnyNull;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService{

    private final BookRepository bookRepository;
    private final CommentRepository commentRepository;
    private final GenreServiceImpl genreService;
    private final AuthorService authorService;

    @Override
    public long create(String name) {
        Book book = new Book(name);
        bookRepository.save(book);
        return book.getId();
    }

    @Override
    public void update(long id, String name) {
        var book = bookRepository.findById(id);
        if (book.isPresent()) {
            book.get().setName(name);
        }
    }

    @Override
    public Book readById(long id) {
        return bookRepository.findById(id).orElse(null);
    }

    @Override
    public List<Book> readAll() {
        return bookRepository.findAll();
    }

    @Override
    public void delete(long id) {
        bookRepository.deleteById(id);
    }

    @Transactional
    @Override
    public boolean addBookComment(long id, String content) {
        Book book = readById(id);
        if (checkAnyNull(book)) {
            return false;
        };
        Comment comment = new Comment(0, content, book);
        commentRepository.save(comment);
        return true;
    }

    @Transactional
    @Override
    public boolean addBookGenre(long id, long genreId) {
        Book book = readById(id);
        Genre genre = genreService.readById(genreId);
        if (checkAnyNull(book, genre)) {
            return false;
        }
        book.getGenres().add(genre);
        return true;
    }

    @Transactional
    @Override
    public boolean addBookAuthor(long id, long authorId) {
        Book book = readById(id);
        var author = authorService.readById(authorId);
        if (checkAnyNull(book, author)) {
            return false;
        }
        book.getAuthors().add(author);
        return true;
    }

    @Transactional
    @Override
    public boolean deleteBookComment(long id, long commentId) {
        Book book = readById(id);
        Comment comment = commentRepository.findById(commentId).orElse(null);
        if (checkAnyNull(book, comment)) {
            return false;
        }
        commentRepository.deleteById(commentId);
        return true;
    }

    @Transactional
    @Override
    public boolean deleteBookGenre(long id, long genreId) {
        Book book = readById(id);
        Genre genre = genreService.readById(genreId);
        if (checkAnyNull(book, genre)) {
            return false;
        }
        book.getGenres().remove(genre);
        return true;
    }

    @Transactional
    @Override
    public boolean deleteBookAuthor(long id, long authorId) {
        Book book = readById(id);
        var author = authorService.readById(authorId);
        if (checkAnyNull(book, author)) {
            return false;
        }
        book.getAuthors().remove(author);
        return true;
    }

}
